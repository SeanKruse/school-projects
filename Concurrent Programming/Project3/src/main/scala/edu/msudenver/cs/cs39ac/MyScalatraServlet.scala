/* @author Sean Kruse - Collobarated with Christian Lopez, Christian Mudd, And Alexander Sanford
   @sources Learning Concurrent Programming in Scala: @author Aleksandar Prokopec.
            Scalatra in Action: @authors Dave Hrycyszyn, Stefan Olligenger , Ross Baker.
            Dr. Beaty
            https://www.baeldung.com/scala/check-file-path-exists
            https://scalatra.org/guides/2.5/http/actions.html
4/13/2022 - Got environment set up and running
4/15/2022 - Started scala review with Christian Lopez. We wrote methods in a shared review file and started tinkering with different objectives to learn the basics
4/18/2022 - Implemented buildfile method using the back to the future slide that Dr. Beaty provided
            (Sourced from Learning Concurrent Programming in Scala: @author Aleksandar Prokopec)
4/22/2022 - Started reading Scalatra in Action: @authors Dave Hrycyszyn, Stefan Olligenger , Ross Baker and implementing file serving methods
4/23/2022 - I got the file to be served up by Jetty, found example code that Dr. Beaty provided on Canvas to get the file contents wrapped in a future
            and got the future to be served up to localhost
4/24/2022 - Implemented some case switching to handle the file not found(Christian Lopez implmented a method that we worked on previously and adapted it to work with the code provided by Dr. Beaty)
            Some finishing touches were added. Handled the 404 better, added a test, updated the readme file, added instructions to Github.
 */
package edu.msudenver.cs.cs39ac

import org.scalatra._
import scala.io.Source
import java.io.File
import java.nio.file.{Paths, Files}
import scala.concurrent.{ExecutionContext, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import org.scalatra.{AsyncResult, FutureSupport, ScalatraServlet}
import org.scalatra.{InternalServerError, NotFound, ScalatraServlet}
import scala.concurrent.{Await, Future}
import scala.concurrent.duration.{Duration, MILLISECONDS}

class MyScalatraServlet extends ScalatraServlet {

  // Files.exists(Paths.get("src/main/twirl/views/hello.scala.html")) match {
  // case true  => print("\n File found \n")
  // case false => print("File Not Found")
  // }

  // Code snippet provided by Dr. Beaty - posted on Canvas
  def getFileContents(filename: String): Future[String] = Future {
    val f = Source.fromFile(filename)
    try f.getLines.mkString("\n")
    finally f.close()
  }

  get("/:URL") {
    contentType = "text/html"
    Files.exists(Paths.get(params("URL"))) match {
      // Code snippet provided by Dr. Beaty
      case true => {
        val s = Await.ready(
          getFileContents(params("URL")),
          Duration(100, MILLISECONDS)
        ); s.value.get.get
      }
      case false => {
        halt(
          404,
          <h1>404: File not found</h1>
        )
      }
    }
  }

  // Scalatra in Action (Code Example): Authors @Dave Hrycyszyn, @Stefan Olligenger , @Ross Baker
  // I shared these resources with Christian Mudd and Christian Lopez
  // notFound {
  // serveStaticResource() getOrElse halt(
  // 404,
  // <h1>404: URL not found, navigate to "localhost:8080/hello.scala.html" </h1>
  // )
  // }

  // Scalatra in Action (Code Example): Authors @Dave Hrycyszyn, @Stefan Olligenger, @Ross Baker
  // I shared these resources with Christian Mudd and Christian Lopez
  error { case e: Exception =>
    InternalServerError(<h1>500: Oops, something went wrong</h1>)
  }
}
