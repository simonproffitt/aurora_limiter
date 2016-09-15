package aurora_limiter

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import sys.process._

object aurora_limiter {

  def create_job(cli_string:String) : Future[Int] = {
     val command = s"aurora job create --wait_until=FINISHED $cli_string"
     val os = sys.props("os.name").toLowerCase
     val oscommand = os match {
       case x if x contains "windows" => "cmd /C " + command
       case _ => command
      }
      Future[Int]{oscommand!}
      }

  def create_jobs(cli_strings:List[String])= {
   Future.sequence(cli_strings.map{x=>create_job(x)})
  }



}
