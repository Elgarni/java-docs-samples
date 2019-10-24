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

// [START translate_translate_text_with_model]
import com.google.cloud.translate.v3.LocationName;
import com.google.cloud.translate.v3.TranslateTextRequest;
import com.google.cloud.translate.v3.TranslateTextResponse;
import com.google.cloud.translate.v3.Translation;
import com.google.cloud.translate.v3.TranslationServiceClient;

import java.io.IOException;

public class TranslateTextWithModel {

  public static void translateTextWithModel() throws IOException {
    // TODO(developer): Replace these variables before running the sample.
    String projectId = "[Google Cloud Project ID]";
    String location = "us-central1";
    String sourceLanguage = "en";
    String targetLanguage = "ja";
    String text = "Hello, world!";
    String modelId = "[Your Model ID]";
    translateTextWithModel(projectId, location, sourceLanguage, targetLanguage, text, modelId);
  }

  // Translating Text with Model
  public static void translateTextWithModel(
      String projectId,
      String location,
      String sourceLanguage,
      String targetLanguage,
      String text,
      String modelId)
      throws IOException {

    // Initialize client that will be used to send requests. This client only needs to be created
    // once, and can be reused for multiple requests. After completing all of your requests, call
    // the "close" method on the client to safely clean up any remaining background resources.
    try (TranslationServiceClient client = TranslationServiceClient.create()) {
      LocationName parent = LocationName.of(projectId, location);
      String modelPath =
          String.format("projects/%s/locations/%s/models/%s", projectId, location, modelId);
      TranslateTextRequest request =
          TranslateTextRequest.newBuilder()
              .setParent(parent.toString())
              .setMimeType("text/plain") // Optional. Can be "text/plain" or "text/html".
              .setSourceLanguageCode(sourceLanguage)
              .setTargetLanguageCode(targetLanguage)
              .addContents(text)
              .setModel(modelPath)
              .build();

      TranslateTextResponse response = client.translateText(request);

      // Display the translation for each input text provided
      for (Translation translation : response.getTranslationsList()) {
        System.out.printf("Translated text: %s\n", translation.getTranslatedText());
      }
    }
  }
}
// [END translate_translate_text_with_model]
