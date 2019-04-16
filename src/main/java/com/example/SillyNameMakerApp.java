/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example;

import com.google.actions.api.ActionRequest;
import com.google.actions.api.ActionResponse;
import com.google.actions.api.DialogflowApp;
import com.google.actions.api.ForIntent;
import com.google.actions.api.response.ResponseBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SillyNameMakerApp extends DialogflowApp {

  private static final Logger LOGGER = LoggerFactory.getLogger(SillyNameMakerApp.class);

  // Note: Do not store any state as an instance variable.
  // It is ok to have final variables where the variable is assigned a value in
  // the constructor but remains unchanged. This is required to ensure thread-
  // safety as the entry point (ActionServlet/ActionsAWSHandler) instances may
  // be reused by the server.

  @ForIntent("make_name")
  public ActionResponse make_name(ActionRequest request) {
    LOGGER.info("make_name start.");
    Double number = (Double) request.getParameter("number");
    String response =
        "Alright, your silly name is "
            + request.getParameter("color")
            + " "
            + (number % 1 == 0 ? Integer.toString(number.intValue()) : number)
            + "! I hope you like it. See you next time.";

    ResponseBuilder responseBuilder = getResponseBuilder(request).add(response).endConversation();
    ActionResponse actionResponse = responseBuilder.build();
    LOGGER.info(actionResponse.toString());
    LOGGER.info("make_name end.");
    return actionResponse;
  }
}
