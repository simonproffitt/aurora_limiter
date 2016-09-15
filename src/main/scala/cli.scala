package aurora_limiter

import scala.concurrent.{Future,Await}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._



object cli {
  def main(args:Array[String]) {
    val source = scala.io.Source.fromFile(args(0))
    val lines = try source.mkString.split("\n").toList finally source.close()
    val results = Await.result(aurora_limiter.create_jobs(lines),30 day)
    println("Failed Jobs: ")
    println((lines zip results).filter{x=>x._2 !=0}.map{x=>x._1})
}
}
