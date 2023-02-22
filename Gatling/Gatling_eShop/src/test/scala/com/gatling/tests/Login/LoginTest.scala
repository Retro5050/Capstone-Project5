package com.gatling.tests.Login

import io.gatling.core.Predef.*
import io.gatling.http.Predef.*
import io.gatling.jdbc.Predef.*

import scala.concurrent.duration.*
import com.gatling.tests.Modules.Protocols.*
import com.gatling.tests.Modules.NavigationModules.*
class LoginTest extends Simulation {

	val users1 = scenario("Users1").exec(homePage, loginPage, login, homePage)

	val users2 = scenario("Users2").exec(homePage, loginPage, login, homePage)

	setUp(
		users1.inject(rampUsers(6).during(10)),
		users2.inject(rampUsers(4).during(10))
	).protocols(httpProtocolEShop)
}