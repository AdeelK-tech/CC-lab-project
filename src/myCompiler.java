import Utili.myUtil;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class myCompiler {
     
     String [][] keywords = {
         {"int","dataType"},
         {"float","dataType"},
         {"String","dataType"},
         {"double","dataType"},
         {"char","dataType"},
         { "class","class"},
         { "void","void"},
         {"do","do"},
        {"while","while"},
        {"if","if"},
        {"else","else"},
    {"return","return"},
        {"public", "accessModifier"},
        {
             "private",
             "accessModifier"
        },
        {
             "protected",
             "accessModifier"
        },
        {
             "static",
             "static"
        },
        {
             "abstract",
             "abstract"
        }
        ,{
             "final",
             "final"
        },
        {
             "new",
             "new"
        },
        {
             "this",
             "this"
        },
        {
             "super",
             "super"
        }
     };
     public String[][] operators={
         {"+","pm"},
         {"-","pm"},
         {"*","mdm"},
         {"/","mdm"},
         {"%","mdm"},
         {"<","RO"},
         {">","RO"},
         {"<=","RO"},
         {">=","RO"},
         {"==","RO"},
         {"!=","RO"},
         {"&&","&&"},
         {"||","||"},
         {
         "!","!"
         },
         {"++","inc/dec"},
         {"--","inc/dec"}
     };
     public String[] punctuators={";",":",",","(",")","{","}","[","]","."};


     public String isKW(String s){
         for (int i=0;i<keywords.length;i++) {
             if (s.equals(keywords[i][0])) {
                 return keywords[i][1];
             }
         }
         return "";
     };
     public String isOp(String s){
     for(int i=0;i<operators.length;i++){
     if(s.equals(operators[i][0])){
     return operators[i][1];
     }
     }
     return "";
     };
public boolean isPunctuator(String s){
         for (String punctuator : punctuators) {
             if (s.equals(punctuator)) {
                 return true;
             }
         }
return false;
};  

public boolean isIdentifier(String s){
Pattern p=Pattern.compile("[_a-zA-Z][_a-zA-Z0-9]*");
Matcher m=p.matcher(s);
return m.matches();
};

public boolean isChar(String s){
Pattern p=Pattern.compile("'(\\\\[tvrnafb\\\\]|[^\\\\'])'");
Matcher m=p.matcher(s);
return m.matches();
};

public boolean isDouble(String s){
Pattern p=Pattern.compile("^[+-]?(0|([1-9][0-9]*))(\\.[0-9]+)?$");
Matcher m=p.matcher(s);
return m.matches();
};

public boolean isString(String s){
Pattern p=Pattern.compile("\".*?\"");
Matcher m=p.matcher(s);
return m.matches();
};

public boolean isIntconst(String s){
Pattern p=Pattern.compile("[+-]?[0-9]+");
Matcher m=p.matcher(s);
return m.matches();
};


 public ArrayList <String> breakWords(String fileInput){
        String temp="";
         ArrayList <String> allChars = new ArrayList();
         //"as"
        for(int i=0;i<fileInput.length();i++){
           
            if(fileInput.charAt(i)=='"'){
                    //string conditions
                if(!temp.isEmpty())
                {
                     allChars.add(temp);
                               temp="";
                }
                             
                     do{
                             
                            temp+= fileInput.charAt(i);
                            i++;
                            
                            if(i>=fileInput.length())
                            {
                               allChars.add(temp);
                               temp="";
                                break;
                            }
                            else if(fileInput.charAt(i)=='\r' && fileInput.charAt(i+1)=='\n')
                            {
                                
                                allChars.add(temp);
                                temp="";
                                i++;
                                temp+= fileInput.charAt(i);
                                allChars.add(temp);
                                temp="";
                                break;
                            }
                     }while(fileInput.charAt(i)!='"');
                      
                       if( myUtil.ifDoubQuo(fileInput, i)) //end on "
                    {
                       
                        temp+= fileInput.charAt(i);
                        allChars.add(temp);
                        temp="";
                     }
                    
                
                    }
                else if(fileInput.charAt(i)=='\''){
                      //char conditions
               
                     do{
                             if(!temp.isEmpty())
                {
                            allChars.add(temp);
                               temp="";
                }
                            
                            if(i>=fileInput.length())
                            {
                               allChars.add(temp);
                               temp="";
                                break;
                            }
                            else if(fileInput.charAt(i)=='\r' && fileInput.charAt(i+1)=='\n')
                            {
                                
                                allChars.add(temp);
                                temp="";
                                i++;
                                temp+= fileInput.charAt(i);
                                allChars.add(temp);
                                temp="";
                                break;
                            }
                     }while(fileInput.charAt(i)!='\'');
                      
                       if( myUtil.ifDoubQuo(fileInput, i)) //end on "
                    {
                       
                        temp+= fileInput.charAt(i);
                        allChars.add(temp);
                        temp="";
                     }
                    
                    }
                else if(fileInput.charAt(i)=='/')
                { //comment conditions
                
                    if(fileInput.charAt(i+1)=='*')
                    {  
                         if(i>0)  // /   0
                           {
                             char a = fileInput.charAt(i-1);
                             System.out.print(fileInput.charAt(i-1));
                             if(fileInput.charAt(i-1)!=' ' && fileInput.charAt(i-1)!='\r'   )
                             {
                                 
                                  temp+= fileInput.charAt(i-1);
                            allChars.add(temp);
                           temp="";
                             }
                           }
                       
                           
                             
                           
                            temp+= fileInput.charAt(i); 
                            i++;
                            temp+= fileInput.charAt(i); 
                            i++;
                        while(i<fileInput.length()-1){
                            // /*
                    
                           
                            temp+= fileInput.charAt(i); 
                             i++;
                             if(i>=fileInput.length()-1)
                             {
                                 break;
                             }
                    if(fileInput.charAt(i)=='*')
                   {
                      
                       
                   if(fileInput.charAt(i=i+1)=='/')
                    { 
                         temp+= fileInput.charAt(i=i-1); 
                         i++;
                         temp+= fileInput.charAt(i); 
                          i++;
                         
                          break;
                    }
               }
                        };
                      
                        //allChars.add(temp);
                           temp="";
                       
                    }else if(fileInput.charAt(i)=='/')
                    {
                          if(i>0)  // /   0
                           {
                             char a = fileInput.charAt(i-1);
                             System.out.print(fileInput.charAt(i-1));
                             if(fileInput.charAt(i-1)!=' ' && fileInput.charAt(i-1)!='\r'   )
                             {
                                 
                                  temp+= fileInput.charAt(i-1);
                            allChars.add(temp);
                           temp="";
                             }
                           }
                       
                           temp+= fileInput.charAt(i); 
                            i++;
                            temp+= fileInput.charAt(i); 
                            i++;
                     while(i<fileInput.length()){
                           temp+= fileInput.charAt(i); 
                             i++;
                  if(i<fileInput.length())
                  {
                      if(fileInput.charAt(i)=='\n')
                    { 
                         
                         break;
                    }
                  }
                   
               
                        }
                         // allChars.add(temp);
                          temp="";
                        
                    }
                    
                    
                    
                    
                   
                }
            
        else{
            //general
              if(fileInput.charAt(i)=='\r' || fileInput.charAt(i)==' '){
               if(fileInput.charAt(i+1)=='\n')
            {
         if(!temp.isEmpty())
        {
              allChars.add(temp);
              temp="";
         
        }
         i++;
         temp+= fileInput.charAt(i);
         allChars.add(temp);
         temp="";
       
            }   
                  
               if(!temp.isEmpty() ){
                    
                    allChars.add(temp);
                    temp="";
                }
                  
        }
              else if(fileInput.charAt(i)=='\n')
            {
         if(!temp.isEmpty())
        {
              allChars.add(temp);
              temp="";
        }
         
         temp+= fileInput.charAt(i);
         System.out.println(temp);
         allChars.add(temp);
              temp="";
       
            }

           else if(myUtil.isPunctuator(fileInput.charAt(i))){
                    
               
               if(fileInput.charAt(i)=='.' && myUtil.isNumeric(temp))
               {
                     temp+= fileInput.charAt(i);
               }
               else{
               if(!temp.isEmpty() ){
                     
                    allChars.add(temp);
                    temp="";
                }
                  
                    temp+= fileInput.charAt(i);
                  
                    allChars.add(temp);
                    temp="";
               }
                
                
            } else if(myUtil.isOperator(fileInput.charAt(i)))
            {
                
                
                if(i<fileInput.length()-1)   //a+   1 2
                {
                        if(myUtil.shouldConcatNum(temp,fileInput.charAt(i),fileInput.charAt(i+1)))
                  {
                            if(!temp.isEmpty() && temp!=" ")
                    {
                     allChars.add(temp);
                      temp="";
                      
                    }
                      
                    if((fileInput.charAt(i)=='+' || fileInput.charAt(i)=='-') && ( Character.isLetter(fileInput.charAt(+1)) || myUtil.isNumeric(String.valueOf(fileInput.charAt(i+1))))) 
                    {
                        temp+=fileInput.charAt(i);
                    i++;
                    temp+= fileInput.charAt(i);
                    
                    }
                    else{
                         temp+=fileInput.charAt(i);
                    i++;
                    temp+= fileInput.charAt(i);
                    allChars.add(temp);
                    temp="";
                    }  
                   
                   
                    

                  } 
                    
                }
             
                  else
                  {
                     if(!temp.isEmpty() && temp!=" ")
                    {
                     allChars.add(temp);
                      temp="";
                      
                    }
                     
                    temp+= fileInput.charAt(i);
                    allChars.add(temp);
                    temp="";

                            
                      
                  }
        }  
            else
            {
              
                     temp+= fileInput.charAt(i);
                
            }
        }
        }
        if(!temp.isEmpty())
        {
              allChars.add(temp);
              temp="";
        }
       
      return allChars;  
    }
      
 
 public  ArrayList<Token> generateTokens(ArrayList<String> words)
 {
     ArrayList<Token> tokenList = new ArrayList<Token>();
     
      return tokenList;
 }
 
}


    
    

