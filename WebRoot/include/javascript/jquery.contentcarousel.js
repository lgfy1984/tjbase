(function($) {
	var	aux		= {
			// navigates left / right
			navigate	: function( dir, $el, $wrapper, opts, cache ) {
				if( dir === 1 ) {
					var $last = $wrapper.children().last();
					$wrapper.find('div.ca-item').each(function(i) {
						$(this).animate(
								{'left': '-=' + cache.itemW + 'px'}, 
								400,
								opts.sliderEasing,
								function(){
									if(i == cache.totalItems - 1){
										//如果最后一个item的最右边的x坐标小于右边框的x坐标
										if(($last.offset().left + $last.width()) <= ($wrapper.offset().left + $wrapper.width())){
											$el.find("span.ca-nav-next").addClass("ca-nav-next-disable");
										}
										cache.isAnimating	= false;
									}
								}
							);
					});
					var $prev = $el.find("span.ca-nav-prev");
					$prev.removeClass("ca-nav-prev-disable");
				}
				else {
					var $first = $wrapper.children().first();
					$wrapper.find('div.ca-item').each(function(i) {
						$(this).animate(
								{'left': '+=' + cache.itemW + 'px'},
								400,
								opts.sliderEasing,
								function(){
									if(i == cache.totalItems - 1){
										//如果第一个item的最左边x坐标大于等于左边框的x坐标
										if($first.offset().left >= ($wrapper.offset().left)){
											$el.find("span.ca-nav-prev").addClass("ca-nav-prev-disable");
										}
										cache.isAnimating	= false;
									}
								}
							);
					});
					$el.find("span.ca-nav-next").removeClass("ca-nav-next-disable");
					
				}
			},
			// opens an item (animation) -> opens all the others
			openItem	: function( $wrapper, $item, opts, cache ) {
				cache.idxClicked	= $item.index();
				// the item's position (1, 2, or 3) on the viewport (the visible items) 
				cache.winpos		= aux.getWinPos( $item.position().left, cache );
				$wrapper.find('div.ca-item').not( $item ).hide();
				$item.find('div.ca-content-wrapper').css( 'left', cache.itemW + 'px' ).stop().animate({
					width	: cache.itemW * 2 + 'px',
					left	: cache.itemW + 'px'
				}, opts.itemSpeed, opts.itemEasing)
				.end()
				.stop()
				.animate({
					left	: '0px'
				}, opts.itemSpeed, opts.itemEasing, function() {
					cache.isAnimating	= false;
					cache.expanded		= true;
					
					aux.openItems( $wrapper, $item, opts, cache );
				});
						
			},
			// opens all the items
			openItems	: function( $wrapper, $openedItem, opts, cache ) {
				var openedIdx	= $openedItem.index();
				
				$wrapper.find('div.ca-item').each(function(i) {
					var $item	= $(this),
						idx		= $item.index();
					
					if( idx !== openedIdx ) {
						$item.css( 'left', - ( openedIdx - idx ) * ( cache.itemW * 3 ) + 'px' ).show().find('div.ca-content-wrapper').css({
							left	: cache.itemW + 'px',
							width	: cache.itemW * 2 + 'px'
						});
						
						// hide more link
						aux.toggleMore( $item, false );
					}
				});
			},
			// show / hide the item's more button
			toggleMore	: function( $item, show ) {
				( show ) ? $item.find('a.ca-more').show() : $item.find('a.ca-more').hide();	
			},
			// close all the items
			// the current one is animated
			closeItems	: function( $wrapper, $openedItem, opts, cache ) {
				var openedIdx	= $openedItem.index();
				
				$openedItem.find('div.ca-content-wrapper').stop().animate({
					width	: '0px'
				}, opts.itemSpeed, opts.itemEasing)
				.end()
				.stop()
				.animate({
					left	: cache.itemW * ( cache.winpos - 1 ) + 'px'
				}, opts.itemSpeed, opts.itemEasing, function() {
					cache.isAnimating	= false;
					cache.expanded		= false;
				});
				
				// show more link
				aux.toggleMore( $openedItem, true );
				
				$wrapper.find('div.ca-item').each(function(i) {
					var $item	= $(this),
						idx		= $item.index();
					
					if( idx !== openedIdx ) {
						$item.find('div.ca-content-wrapper').css({
							width	: '0px'
						})
						.end()
						.css( 'left', ( ( cache.winpos - 1 ) - ( openedIdx - idx ) ) * cache.itemW + 'px' )
						.show();
						
						// show more link
						aux.toggleMore( $item, true );
					}
				});
			},
			// gets the item's position (1, 2, or 3) on the viewport (the visible items)
			// val is the left of the item
			getWinPos	: function( val, cache ) {
				switch( val ) {
					case 0 					: return 1; break;
					case cache.itemW 		: return 2; break;
					case cache.itemW * 2 	: return 3; break;
				}
			}
		},
		methods = {
			init 		: function( options ) {
				
				if( this.length ) {
					
					var settings = {
						sliderSpeed		: 500,			// speed for the sliding animation
						sliderEasing	: 'easeOutExpo',// easing for the sliding animation
						itemSpeed		: 500,			// speed for the item animation (open / close)
						itemEasing		: 'easeOutExpo',// easing for the item animation (open / close)
						scroll			: 1				// number of items to scroll at a time
					};
					
					return this.each(function() {
						
						// if options exist, lets merge them with our default settings
						if ( options ) {
							$.extend( settings, options );
						}
						
						var $el 			= $(this),
							$wrapper		= $el.find('div.ca-wrapper'),
							$items			= $wrapper.children('div.ca-item'),
							cache			= {};
						
						// save the with of one item	
						cache.itemW			= $items.width();
						// save the number of total items
						cache.totalItems	= $items.length;
						
						// add navigation buttons
						if( cache.itemW * cache.totalItems > $wrapper.width() )	{
							$el.prepend('<div class="ca-nav"><span class="ca-nav-prev ca-nav-prev-disable">Previous</span><span class="ca-nav-next">Next</span></div>')
						}
						
						// control the scroll value
						if( settings.scroll < 1 )
							settings.scroll = 1;
						else if( settings.scroll > 4 )
							settings.scroll = 4;	
						
						var $navPrev		= $el.find('span.ca-nav-prev'),
							$navNext		= $el.find('span.ca-nav-next');
						
						// hide the items except the first 3
						$wrapper.css( 'overflow', 'hidden' );
						
						// the items will have position absolute 
						// calculate the left of each item
						$items.each(function(i) {
							$(this).css({
								position	: 'absolute',
								left		: i * cache.itemW + 'px'
							});
						});
						
						// click to open the item(s)
						$el.find('a.ca-more').live('click.contentcarousel', function( event ) {
							if( cache.isAnimating ) return false;
							cache.isAnimating	= true;
							$(this).hide();
							var $item	= $(this).closest('div.ca-item');
							aux.openItem( $wrapper, $item, settings, cache );
							return false;
						});
						
						// click to close the item(s)
						$el.find('a.ca-close').live('click.contentcarousel', function( event ) {
							if( cache.isAnimating ) return false;
							cache.isAnimating	= true;
							var $item	= $(this).closest('div.ca-item');
							aux.closeItems( $wrapper, $item, settings, cache );
							return false;
						});
						
						// navigate left
						$navPrev.bind('click.contentcarousel', function( event ) {
							if( cache.isAnimating ) return false;
							if($(this).hasClass("ca-nav-prev-disable")) return false;
							cache.isAnimating	= true;
							aux.navigate( -1, $el, $wrapper, settings, cache );
						});
						
						// navigate right
						$navNext.bind('click.contentcarousel', function( event ) {
							if( cache.isAnimating ) return false;
							if($(this).hasClass("ca-nav-next-disable")) return false;
							cache.isAnimating	= true;
							aux.navigate( 1, $el, $wrapper, settings, cache );
						});
						
						// adds events to the mouse
						$el.bind('mousewheel.contentcarousel', function(e, delta) {
							if(delta > 0) {
								if( cache.isAnimating ) return false;
								cache.isAnimating	= true;
								aux.navigate( -1, $el, $wrapper, settings, cache );
							}	
							else {
								if( cache.isAnimating ) return false;
								cache.isAnimating	= true;
								aux.navigate( 1, $el, $wrapper, settings, cache );
							}	
							return false;
						});
						
					});
				}
			}
		};
	
	$.fn.contentcarousel = function(method) {
		if ( methods[method] ) {
			return methods[method].apply( this, Array.prototype.slice.call( arguments, 1 ));
		} else if ( typeof method === 'object' || ! method ) {
			return methods.init.apply( this, arguments );
		} else {
			$.error( 'Method ' +  method + ' does not exist on jQuery.contentcarousel' );
		}
	};
	
})(jQuery);