import org.scalatest._
import shell._

class ExampleSpec extends FlatSpec with Matchers {

  "A Shell" should "Start and Exit with :exit" in 
     {
     val exitCmd = ":exit"
     val shellCmds = ""

     val ConsoleHarness(stdin, stdout, stderr) = 
         ConsoleHarness(PShell.run, shellCmds, exitCmd)

   println("Input:\n" + stdin)
   println("Output:\n" + stdout)
   println("Error:\n" + stderr)
   }
   
   it should "Throw Exception if exit command not sent" in 
      {
      val exit = ""
      val commands = ""
      
      intercept[Exception] 
         {
         val ConsoleHarness(stdin, stdout, stderr) = 
         ConsoleHarness(PShell.run, commands, exit)
         }
      }

   }


// Console execution harness. Makes it possible to test console
// in a seperate IO context. Runs single-threaded in the main thread.

object ConsoleHarness
   {
   val eol = System.getProperty("line.separator")

   def apply(runapp: => Any, cmds: String, exit: String): Map[String,String] =
      {
      val cmdString = cmds.split(",").map(_.trim).mkString(eol) + eol + exit

      val inS = new java.io.StringReader(cmdString)
      Console.setIn(inS)

      val outS = new java.io.ByteArrayOutputStream
      Console.setOut(outS)

      val errS = new java.io.ByteArrayOutputStream
      Console.setErr (errS)

      runapp

      Map("stdin" -> cmdString, "stdout" -> outS.toString, 
        "stderr" -> errS.toString)
      }

   def unapply(iomap: Map[String,String]): Option[(String, String, String)] =
      {
      Console.setIn(System.in)
      Console.setOut(System.out)
      Console.setErr(System.err)

      if (iomap.size > 0)
         Some(iomap("stdin"), iomap("stdout"), iomap("stderr"))
      else
         None
      }
   }


