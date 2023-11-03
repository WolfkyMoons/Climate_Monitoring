CSVReader r1 = ...; // reader of 1st file
CSVReader r2 = ...; // reader of 2nd file

HashMap<String,String[]> dic = new HashMap<String,String[]>();

int commonCol = 1; // index of the commonColumn

r1.readNext(); // skip header
String[] line = null;
while ((line = r1.readNext()) != null)
{
  dic.add(line[commonCol],line)
}

commonCol = 2; // index of the commonColumn in the 2nd file

r2.readNext(); // skip header
String[] line = null;
while ((line = r2.readNext()) != null)
{
  if (dic.keySet().contains(line[commonCol])
  {
    // append line to existing entry
  }
  else
  {
     // create a new entry and pre-pend it with default values
     // for the columns of file1
  }
}

foreach (String[] line : dic.valueSet())
{
   // write line to the output file.
}

int n1,n2;//stores the serial number of the column that has the duplicate data
    BufferedReader br1=new BufferedReader(new InputStreamReader(new FileInputStream(f1)));
    BufferedReader br2=new BufferedReader(new InputStreamReader(new FileInputStream(f2)));
    String line1,line2;
    while((line1=br1.readLine())!=null && (line2=br2.readLine())!=null){
        String line=line1+","+line2;
        String newL="";
        StringTokenizer st=new StringTokenizer(line,",");
        for(int i=1;i<=st.countTokens();i++){
            if((i==n1)||(i==n1+n2))
                continue;
            else
                newL=newL+","+st.nextToken();
        }
        String l=newL.substring(1);
        //write this line to the output file
    }