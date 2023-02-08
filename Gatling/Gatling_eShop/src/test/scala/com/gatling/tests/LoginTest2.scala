package com.gatling.tests

import io.gatling.core.Predef.*
import io.gatling.http.Predef.*
import io.gatling.jdbc.Predef.*

import scala.concurrent.duration.*

class LoginTest2 extends Simulation {

	val httpProtocol = http.baseUrl("http://host.docker.internal:5100/")

	val scn = scenario("login_test")
		// LogIn
		.exec(http("Home Page")
			.post("/"))
		.pause(2)
		.exec(http("Sign In Page")
			.get("/Account/SignIn"))
		.pause(2)
		.exec(http("login")
			.post("http://host.docker.internal:5105/Account/Login?ReturnUrl=%2Fconnect%2Fauthorize%2Fcallback%3Fclient_id%3Dmvc%26redirect_uri%3Dhttp%253A%252F%252Fhost.docker.internal%253A5100%252Fsignin-oidc%26response_type%3Dcode%2520id_token%26scope%3Dopenid%2520profile%2520orders%2520basket%2520webshoppingagg%2520orders.signalrhub%26response_mode%3Dform_post%26nonce%3D638114766664603610.ODRjNzA4ZDktOGU3ZS00ZWE0LThiMjUtNzkyMGIzMGVhNDk3MTA0NTUwNzItNDBiOC00MWIyLWFjZGEtYTg0NWE0MTM1NTA3%26state%3DCfDJ8HI2B0m15WhGp3yVc3O5P6k5jqgQ8Q4KaAwBvM-DEEFCrow5Imz2ZQigciStbcqNDhHtdviii_jcArZAG4qk5s_p8dLUPwPazFlHHpny7Cb0DvLsqV8LfpeLTuigy9zSZYKhzu9P4ZsJvH-ubSlNPcrRrKmtE3OsTX8u2FL8XrqinuXY8vk0Uv0HTyyX9otmziakUArN37dxanQmOZvnc1RDATJASyXC0wFYpwve2643Gp4Tr7EqZyHtOwuiVKPi79zCHITjb1MRQiBVZV-al4v0k7OWvBEvZbza8z5Gq9VduF3hGspfGoIsetIAjpRYRW8Gxb_iiQICTXPJ9msGfFg%26x-client-SKU%3DID_NETSTANDARD2_0%26x-client-ver%3D6.10.0.0")
			.formParam("Email", "demouser@microsoft.com")
			.formParam("Password", "Pass@word1")
			.check(jsonPath("$..token").exists.saveAs("authToken"))
		)
		.pause(2)

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}