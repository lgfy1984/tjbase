/////////////////////////////////////////////////////////////
////////////////////////   DZENALL   ////////////////////////
//////////////////////// 2008-08-06 /////////////////////////
/////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////////////
// Function: trimSubStr(trimStr,subStr,aWay).
// Description: Remove the string tallies with subStr from 
//	trimStr in someway.
// Param: trimStr--A string being removed if it tallies with subStr.
//	subStr--A string consulting removed.
//	aWay--0=remove all strings what tallies with subStr.
//		  1=remove from left what tallies with subStr.
//		  2=remove from right what tallies with subStr.
// Return: A worked string.
// ie: 	trimSubStr("   usj     29   ","   ",0)		"usj29"
//		trimSubStr("   usj     29   ","   ",1)		"usj     29   "
//		trimSubStr("   usj     29   ","   ",2)		"   usj     29"
/////////////////////////////////////////////////////////////   
function trimSubStr(trimStr, subStr,aWay){       
    var tTrLength,tSbLength,tempLength;   
    var tempString;   
    var i;     

    tTrLength = trimStr.length;   
    tSbLength = subStr.length;   

    if(tSbLength == 0){return trimStr;}   
    if(tSbLength > tTrLength){return trimStr;}   
  
    tempString = trimStr;   
    switch(aWay){   
        case 0://all   
            do{   
                tempLength = tempString.length;   
                tempString = tempString.replace(subStr, "");   
            }while(tempLength != tempString.length);   
            break;     
        case 1://from left   
            while(true){   
                if(tempString.length < tSbLength)  
                	break;   
                for(i = 0; i < tSbLength; i++)   
                    if(subStr.charAt(i) != tempString.charAt(i))     
                        return tempString;   
                tempString = tempString.replace(subStr,"");       
            };       
        case 2://from right  
            while(true){   
                tempLength = tempString.length;   
                if(tempLength < tSbLength){
                	return tempString;
                }   
                for(i = 0; i < tSbLength; i++){   
                    if(subStr.charAt(i) != tempString.charAt(tempLength - tSbLength + i)){   
                        return tempString;   
                    }       
                }           
                tempString = tempString.substr(0,tempLength-tSbLength);     
            };   
        default:   
            return tempString;   
    }   
    return tempString;     
}
/////////////////////////////////////////////////////////////
// Function: FormatNumber(srcStr, nAfterDot).
// Description: Format a number string with dot, and return a rounded number.
// Param: srcStr--A number string being formated.
//		  nAfterDot--A number indicates for decimal digits.
// Return: A worked number.
// ie: 	FormatNumber(3.562454665, 2)		3.56
//		FormatNumber(3.565454665, 2)		3.57
//		FormatNumber(3.566454665, 2)		3.57
///////////////////////////////////////////////////////////// 
function FormatNumber(srcStr, nAfterDot){
	var srcStr,nAfterDot;
	var resultStr,nTen;
	srcStr = ""+srcStr+"";
	strLen = srcStr.length;
	dotPos = srcStr.indexOf(".",0);
	if (dotPos == -1){
		resultStr = srcStr+".";
		for (i=0;i<nAfterDot;i++){
			resultStr = resultStr+"0";
		}
	}else{
		if ((strLen - dotPos - 1) >= nAfterDot){
			nAfter = dotPos + nAfterDot + 1;
			nTen =1;
			for(j=0;j<nAfterDot;j++){
				nTen = nTen*10;
			}
			resultStr = Math.round(parseFloat(srcStr)*nTen)/nTen;
		}else{
			resultStr = srcStr;
			for (i=0;i<(nAfterDot - strLen + dotPos + 1);i++){
				resultStr = resultStr+"0";
			}
		}
	}
	return resultStr;
}  