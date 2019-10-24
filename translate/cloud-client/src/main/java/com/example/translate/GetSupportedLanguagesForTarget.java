/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.translate;

// [START translate_get_supported_languages_for_target]
import com.google.cloud.translate.v3.GetSupportedLanguagesRequest;
import com.google.cloud.translate.v3.LocationName;
import com.google.cloud.translate.v3.SupportedLanguage;
import com.google.cloud.translate.v3.SupportedLanguages;
import com.google.cloud.translate.v3.TranslationServiceClient;

import java.io.IOException;

public class GetSupportedLanguagesForTarget {

  public static void getSupportedLanguagesForTarget() throws IOException {
    // TODO(developer): Replace these variables before running the sample.
    String projectId = "[Google Cloud Project ID]";
    String location = "global";
    String languageCode = "is";
    getSupportedLanguagesForTarget(projectId, location, languageCode);
  }

  // Listing supported languages with target language name
  public static void getSupportedLanguagesForTarget(
      String projectId, String location, String languageCode) throws IOException {

    // Initialize client that will be used to send requests. This client only needs to be created
    // once, and can be reused for multiple requests. After completing all of your requests, call
    // the "close" method on the client to safely clean up any remaining background resources.
    try (TranslationServiceClient client = TranslationServiceClient.create()) {
      LocationName parent = LocationName.of(projectId, location);
      GetSupportedLanguagesRequest request =
          GetSupportedLanguagesRequest.newBuilder()
              .setParent(parent.toString())
              .setDisplayLanguageCode(languageCode)
              .build();

      SupportedLanguages response = client.getSupportedLanguages(request);

      // List language codes of supported languages
      for (SupportedLanguage language : response.getLanguagesList()) {
        System.out.printf("Language Code: %s\n", language.getLanguageCode());
        System.out.printf("Display Name: %s\n", language.getDisplayName());
      }
    }
  }
}
// [END translate_get_supported_languages_for_target]
