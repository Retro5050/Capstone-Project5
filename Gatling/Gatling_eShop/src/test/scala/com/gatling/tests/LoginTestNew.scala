package com.gatling.tests

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class LoginTestNew extends Simulation {

	val httpProtocol = http
		.baseUrl("http://host.docker.internal:5100")
		.inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*detectportal\.firefox\.com.*""", """.*\.PNG""", """.*/pic/""", """.*\.svg""", """.*/js/site.js.*""", """.*/hub/notificationhub/.*"""), WhiteList())
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-US,en;q=0.9")
		.upgradeInsecureRequestsHeader("1")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36")

	val uri1 = "host.docker.internal"

	val scn = scenario("LoginTestNew")
		.exec(http("request_0")
			.get("/"))
		.pause(2)
		.exec(http("request_1")
			.get("/Account/SignIn"))
		.pause(3)
		.exec(http("request_2")
			.post("http://" + uri1 + ":5105/Account/Login?returnurl=%2Fconnect%2Fauthorize%2Fcallback%3Fclient_id%3Dmvc%26redirect_uri%3Dhttp%253A%252F%252Fhost.docker.internal%253A5100%252Fsignin-oidc%26response_type%3Dcode%2520id_token%26scope%3Dopenid%2520profile%2520orders%2520basket%2520webshoppingagg%2520orders.signalrhub%26response_mode%3Dform_post%26nonce%3D638114771572014121.MzkzNGZjMWMtY2RmZi00MGZlLWI5ZWEtN2YxNDM5ZDRjYzFkNDhmNGJhMDMtZmQyNS00YjIxLTg2ZDAtNjQ1OTcxNTQyMzli%26state%3DCfDJ8HI2B0m15WhGp3yVc3O5P6nbsUHILFFGy31saWqD9dZ9spkbGi-T6rldQn74iIv09wTtOwdtqoizQlKwBui0Ne_SxXHbZy5a3_b32HyF4wlAQDpOK1a1U0vx5aurZ2Fw7moWloo_5kIqQT8Bnwj_kg_oJoX58ZRCCZl2NKtUwxwtADvcwchvOrCi_PIGBTbk1gPP4PR2yeqS44gxRGKuPCjaYvhbyVnHrOEHSdEF6Cuegf1OyHi01DTH2jr-CQAmfboL1BCcqdg8SUVDL6nWECKmmn3EmYhFuOTNk1-OnTir5keFI4CIIH46YwqTquEH8l3m1fmJCmVU2qnJ-_vMM1E%26x-client-SKU%3DID_NETSTANDARD2_0%26x-client-ver%3D6.10.0.0")
			.formParam("ReturnUrl", "/connect/authorize/callback?client_id=mvc&redirect_uri=http%3A%2F%2Fhost.docker.internal%3A5100%2Fsignin-oidc&response_type=code%20id_token&scope=openid%20profile%20orders%20basket%20webshoppingagg%20orders.signalrhub&response_mode=form_post&nonce=638114771572014121.MzkzNGZjMWMtY2RmZi00MGZlLWI5ZWEtN2YxNDM5ZDRjYzFkNDhmNGJhMDMtZmQyNS00YjIxLTg2ZDAtNjQ1OTcxNTQyMzli&state=CfDJ8HI2B0m15WhGp3yVc3O5P6nbsUHILFFGy31saWqD9dZ9spkbGi-T6rldQn74iIv09wTtOwdtqoizQlKwBui0Ne_SxXHbZy5a3_b32HyF4wlAQDpOK1a1U0vx5aurZ2Fw7moWloo_5kIqQT8Bnwj_kg_oJoX58ZRCCZl2NKtUwxwtADvcwchvOrCi_PIGBTbk1gPP4PR2yeqS44gxRGKuPCjaYvhbyVnHrOEHSdEF6Cuegf1OyHi01DTH2jr-CQAmfboL1BCcqdg8SUVDL6nWECKmmn3EmYhFuOTNk1-OnTir5keFI4CIIH46YwqTquEH8l3m1fmJCmVU2qnJ-_vMM1E&x-client-SKU=ID_NETSTANDARD2_0&x-client-ver=6.10.0.0")
			.formParam("Email", "demouser@microsoft.com")
			.formParam("Password", "Pass@word1")
			.formParam("__RequestVerificationToken", "CfDJ8G_Nzkb2tfhIh0rfCChTaUVfuUhSGaHJDUe4dLqZUyUxJ9twsmJ2ISaOSivHv7Zn5RYDFZNut0MV8L0jb-gCDQ_AV6iUbQHx-tydB_UnPRuExKkGK1_z4QEJXTYe7F8rgAZ-BEGA0hyhyCECG6LOlCc")
			.formParam("RememberMe", "false")
			.resources(http("request_3")
			.post("/signin-oidc")))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}