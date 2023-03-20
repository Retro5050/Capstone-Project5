package com.gatling.tests.Modules

import io.gatling.core.Predef.*
import io.gatling.http.Predef.*
import io.gatling.jdbc.Predef.*
import com.gatling.tests.Modules.HeaderModules.*
import com.gatling.tests.Modules.LoginModule.*

object AdminModules {

  //Go to configuration page and get needed resources
  val configPage = exec(http("Config List Page")
    .get("/admin_config.html")
    .headers(mainPageHeader)
    .resources(http("Get Configs")
      .get("/api/v1/adminbasicservice/adminbasic/configs")
      .headers(apiV1Header)))
    .pause(6)

  //Go to contacts page and get needed resources
  val contactsPage = exec(http("Contacts List Page")
    .get("/admin_contacts.html")
    .headers(mainPageHeader)
    .resources(http("Get Contacts")
      .get("/api/v1/adminbasicservice/adminbasic/contacts")
      .headers(apiV1Header)))
    .pause(5)

  //Go to price list page and get needed resources
  val pricePage = exec(http("Price List Page")
    .get("/admin_price.html")
    .headers(mainPageHeader)
    .resources(http("Get Prices")
      .get("/api/v1/adminbasicservice/adminbasic/prices")
      .headers(apiV1Header)))
    .pause(5)

  //Go to route list page and get needed resources
  val routePage = exec(http("Route List Page")
    .get("/admin_route.html")
    .headers(mainPageHeader)
    .resources(http("Get Routes")
      .get("/api/v1/adminrouteservice/adminroute")
      .headers(apiV1Header)))
    .pause(4)

  //Go to station list page and get needed resources
  val stationPage = exec(http("Station List Page")
    .get("/admin_station.html")
    .headers(mainPageHeader)
    .resources(http("Get Stations")
      .get("/api/v1/adminbasicservice/adminbasic/stations")
      .headers(apiV1Header)))
    .pause(5)

  //Go to train list page and get needed resources
  val trainPage = exec(http("Train List Page")
    .get("/admin_train.html")
    .headers(mainPageHeader)
    .resources(http("Get Trains")
      .get("/api/v1/adminbasicservice/adminbasic/trains")
      .headers(apiV1Header)))
    .pause(7)

  //Go to travel list page and get needed resources
  val travelPage = exec(http("Travel List Page")
    .get("/admin_travel.html")
    .headers(mainPageHeader)
    .resources(http("Get Trains")
      .get("/api/v1/trainservice/trains")
      .headers(apiV1Header),
      http("Get Stations")
        .get("/api/v1/stationservice/stations")
        .headers(apiV1Header),
      http("Get Routes")
        .get("/api/v1/routeservice/routes")
        .headers(apiV1Header),
      http("Get Travel Info")
        .get("/api/v1/admintravelservice/admintravel")
        .headers(apiV1Header)))
    .pause(6)

  //Go to user list page and get needed resources
  val userPage = exec(http("User List Page")
    .get("/admin_user.html")
    .headers(mainPageHeader)
    .resources(http("Get Users")
      .get("/api/v1/adminuserservice/users")
      .headers(apiV1Header)))
    .pause(5)
}
