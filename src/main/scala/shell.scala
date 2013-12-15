package shell 
{

/* example console application: a shell which does pretty much nothing */

case object PShell
   {
   def run =
      {
      val prompt = coding(System.getProperty("file.encoding")) + " "
      
      var line: String = new String

      while (line != ":exit") // :exit ends this application
         {
         Console.print(prompt.toString)
         line = Console.readLine.toString


         // Console.out.println("[" + line + "]")
         //Console.out.println(line)
         }
      }

   // if run with: scala -Dfile.encoding=UTF-8 Pshell, you get cool
   // Unicode arrow prompt viewable with: xterm -en UTF-8
   // otherwise, it uses plain carrot prompt

   private def coding(x: Any) = x match 
      {
      case "UTF-8" => 0x2794.toChar
      case _ => '>'
      }
   }
}
