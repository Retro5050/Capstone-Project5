package com.gatling.tests.Modules

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

import HeaderModules.*

object NavigationModules {

  val homePage = exec(http("Home Page")
    .get("/index.html")
    .headers(mainPageHeader))
    .pause(2)

  val viewOrderListPage = exec(http("View Order List")
    .get("/client_order_list.html")
    .headers(orderListHeader)
    .resources(http("Refresh Page Test")
      .post("/api/v1/orderservice/order/refresh")
      .headers(apiV1Header)
      .body(RawFileBody("com/gatling/tests/OrderList/account_request.json")),
      http("Refresh Page")
        .post("/api/v1/orderOtherService/orderOther/refresh")
        .headers(apiV1Header)
        .body(RawFileBody("com/gatling/tests/OrderList/account_request.json"))))
    .pause(8)

  val payTicket = exec(http("Confirm Payment")
    .post("/api/v1/inside_pay_service/inside_payment")
    .headers(apiV1Header)
    .body(RawFileBody("com/gatling/tests/OrderList/orderlistpayticket/payment_request.json")))
    .pause(4)

  val cancelOrder = exec(http("Select Cancel Order")
    .get("/api/v1/cancelservice/cancel/refound/cfd74f18-9135-422f-8c16-73aa8e019059")
    .headers(apiV1Header))
    .pause(4)
    .exec(http("View Cancellation Message")
      .get("/api/v1/cancelservice/cancel/cfd74f18-9135-422f-8c16-73aa8e019059/4d2a46c7-71cb-4cf1-b5bb-b68406d9da6f")
      .headers(apiV1Header))
    .pause(2)
  
  val changeOrder = exec(http("Select Change Order")
    .post("/api/v1/travelservice/trips/left")
    .headers(apiV1Header)
    .body(RawFileBody("com/gatling/tests/OrderList/select_trip_form.json")))
    .pause(4)
    .exec(http("Confirm Rebook")
      .post("/api/v1/rebookservice/rebook")
      .headers(apiV1Header)
      .body(RawFileBody("com/gatling/tests/OrderList/rebook_form.json")))
    .pause(7)

  val viewConsign = exec(http("View Consign")
    .get("/api/v1/consignservice/consigns/order/8c019509-7b40-44c2-803f-a15c17f83b1e")
    .headers(apiV1Header))
    .pause(6)

  val updateConsign = exec(http("Update Consign")
    .put("/api/v1/consignservice/consigns")
    .headers(apiV1Header)
    .body(RawFileBody("com/gatling/tests/OrderList/update_consign_form.json")))
    .pause(3)

  val searchTrip = exec(http("Search for Trip")
    .post("/api/v1/travel2service/trips/left")
    .headers(searchTripHeader)
    .body(RawFileBody("com/gatling/tests/Booking/search_form.json")))
    .pause(6)

  //Break this up into defined parts: assurance, contacts, food service
  /** Custom Contact Steps
   * exec(http("Custom Contact Form")
   * .post("/api/v1/contactservice/contacts")
   * .headers(headers_9)
   * .body(RawFileBody("com/gatling/tests/Booking/booking1stclass2/custom_contact_form.json"))
   * .resources(http("Custom Contact Form")
   * .get("/api/v1/contactservice/contacts/account/4d2a46c7-71cb-4cf1-b5bb-b68406d9da6f")
   * .headers(headers_6)))
   */
  val selectTrip = exec(http("Select Trip") /** change seat_price to 22.5 for economy*/
    .get("/client_ticket_book.html?tripId=D1345&from=shanghai&to=suzhou&seatType=2&seat_price=50.0&date=2023-02-16")
    .headers(selectTripHeader)
    .resources(http("Select No Assurance")
      .get("/api/v1/assuranceservice/assurances/types")
      .headers(apiV1Header),
      http("Select Saved Contact 2")
        .get("/api/v1/contactservice/contacts/account/4d2a46c7-71cb-4cf1-b5bb-b68406d9da6f")
        .headers(apiV1Header),
      http("Select Food Service")
        .get("/api/v1/foodservice/foods/2023-02-16/shanghai/suzhou/D1345")
        .headers(apiV1Header)))
    .pause(12)

  val submitTripBooking = exec(http("Submit Booking")
    .post("/api/v1/preserveservice/preserve")
    .headers(apiV1Header)
    .body(RawFileBody("com/gatling/tests/Booking/booking_1st_class_form.json")))
    .pause(5)
}
