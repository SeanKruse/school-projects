package edu.msudenver.cs.cs39ac

import org.scalatra.test.scalatest._

class MyScalatraServletTests extends ScalatraFunSuite {

  addServlet(classOf[MyScalatraServlet], "/*")

  test("GET / on MyScalatraServlet should return status 200") {
    get("/") {
      status should equal(200)
    }
  }

  test("GET /hello on MyScalatraServlet should return status 404") {
    get("/hello") {
      status should equal(404)
    }
  }
}
