package com.gatling.tests.BookingTickets

import io.gatling.core.Predef.*
import io.gatling.http.Predef.*
import io.gatling.jdbc.Predef.*

import scala.concurrent.duration.*

class Booking1stClassTest extends Simulation {

	val httpProtocol = http
		.baseUrl("http://192.168.3.205:32677")
		.inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*detectportal\.firefox\.com.*"""), WhiteList())

	val headers_0 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7",
		"Accept-Encoding" -> "gzip, deflate",
		"Accept-Language" -> "en-US,en;q=0.9",
		"Cache-Control" -> "max-age=0",
		"If-Modified-Since" -> "Mon, 29 Aug 2022 07:25:34 GMT",
		"If-None-Match" -> "630c69ee-46c7",
		"Upgrade-Insecure-Requests" -> "1",
		"User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36")

	val headers_1 = Map(
		"Accept" -> "image/avif,image/webp,image/apng,image/svg+xml,image/*,*/*;q=0.8",
		"Accept-Encoding" -> "gzip, deflate",
		"Accept-Language" -> "en-US,en;q=0.9",
		"User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36")

	val headers_3 = Map(
		"Accept" -> "application/json, text/javascript, */*; q=0.01",
		"Accept-Encoding" -> "gzip, deflate",
		"Accept-Language" -> "en-US,en;q=0.9",
		"Content-Type" -> "application/json",
		"Origin" -> "http://192.168.3.205:32677",
		"User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36",
		"X-Requested-With" -> "XMLHttpRequest")

	val headers_6 = Map(
		"Accept" -> "application/json, text/javascript, */*; q=0.01",
		"Accept-Encoding" -> "gzip, deflate",
		"Accept-Language" -> "en-US,en;q=0.9",
		"Content-Type" -> "application/json",
		"User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36",
		"X-Requested-With" -> "XMLHttpRequest",
		"authorization" -> "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmZHNlX21pY3Jvc2VydmljZSIsInJvbGVzIjpbIlJPTEVfVVNFUiJdLCJpZCI6IjRkMmE0NmM3LTcxY2ItNGNmMS1iNWJiLWI2ODQwNmQ5ZGE2ZiIsImlhdCI6MTY3NjM3MTc3OSwiZXhwIjoxNjc2Mzc1Mzc5fQ.v4pgWHlKwxGwavjnyAPfw93VtrNW_j5YjVNTIYc1_OQ")

	val headers_9 = Map(
		"Accept" -> "application/json, text/javascript, */*; q=0.01",
		"Accept-Encoding" -> "gzip, deflate",
		"Accept-Language" -> "en-US,en;q=0.9",
		"Content-Type" -> "application/json",
		"Origin" -> "http://192.168.3.205:32677",
		"User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36",
		"X-Requested-With" -> "XMLHttpRequest",
		"authorization" -> "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmZHNlX21pY3Jvc2VydmljZSIsInJvbGVzIjpbIlJPTEVfVVNFUiJdLCJpZCI6IjRkMmE0NmM3LTcxY2ItNGNmMS1iNWJiLWI2ODQwNmQ5ZGE2ZiIsImlhdCI6MTY3NjM3MTc3OSwiZXhwIjoxNjc2Mzc1Mzc5fQ.v4pgWHlKwxGwavjnyAPfw93VtrNW_j5YjVNTIYc1_OQ")



	val scn = scenario("Booking1stClassTest")
		.exec(http("request_0")
			.get("/index.html")
			.headers(headers_0)
			.resources(http("request_1")
			.get("/api/v1/verifycode/generate")
			.headers(headers_1),
				http("request_2")
			.get("/assets/fonts/fontawesome-webfont.woff2?v=4.6.3")))
		.pause(4)
		.exec(http("request_3")
			.post("/api/v1/travelservice/trips/left")
			.headers(headers_3)
			.body(RawFileBody("com/gatling/tests/Booking/booking1stclasstest/0003_request.json")))
		.pause(3)
		.exec(http("request_4")
			.get("/client_ticket_book.html?tripId=D1345&from=shanghai&to=suzhou&seatType=2&seat_price=50.0&date=2023-02-16")
			.resources(http("request_5")
			.get("/assets/fonts/fontawesome-webfont.woff2?v=4.6.3"),
				http("request_6")
			.get("/api/v1/assuranceservice/assurances/types")
			.headers(headers_6),
				http("request_7")
			.get("/api/v1/contactservice/contacts/account/4d2a46c7-71cb-4cf1-b5bb-b68406d9da6f")
			.headers(headers_6),
				http("request_8")
			.get("/api/v1/foodservice/foods/2023-02-16/shanghai/suzhou/D1345")
			.headers(headers_6)))
		.pause(14)
		.exec(http("request_9")
			.post("/api/v1/contactservice/contacts")
			.headers(headers_9)
			.body(RawFileBody("com/gatling/tests/Booking/booking1stclasstest/0009_request.json"))
			.resources(http("request_10")
			.get("/api/v1/contactservice/contacts/account/4d2a46c7-71cb-4cf1-b5bb-b68406d9da6f")
			.headers(headers_6)))
		.pause(3)
		.exec(http("request_11")
			.post("/api/v1/preserveservice/preserve")
			.headers(headers_9)
			.body(RawFileBody("com/gatling/tests/Booking/booking1stclasstest/0011_request.json")))
		.pause(4)
		.exec(http("request_12")
			.get("/index.html")
			.resources(http("request_13")
			.get("/assets/fonts/fontawesome-webfont.woff2?v=4.6.3"),
				http("request_14")
			.get("/api/v1/verifycode/generate")
			.headers(headers_1),
				http("request_15")
			.get("/api/v1/verifycode/generate")
			.headers(headers_1)))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}