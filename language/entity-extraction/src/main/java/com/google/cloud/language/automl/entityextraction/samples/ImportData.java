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

package com.google.cloud.language.automl.entityextraction.samples;

// [START automl_natural_language_entity_import_data]

import com.google.cloud.automl.v1beta1.AutoMlClient;
import com.google.cloud.automl.v1beta1.DatasetName;
import com.google.cloud.automl.v1beta1.GcsSource;
import com.google.cloud.automl.v1beta1.InputConfig;
import com.google.protobuf.Empty;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

class ImportData {

  // Import data from Google Cloud Storage into a dataset
  static void importData(String projectId, String computeRegion, String datasetId, String[] gcsUris)
      throws InterruptedException, ExecutionException, IOException {
    // String projectId = "YOUR_PROJECT_ID";
    // String computeRegion = "us-central1";
    // String datasetId = "YOUR_DATASET_ID";
    // String[] gcsUris = {"PATH_TO_YOUR_DATA_FILE1", "PATH_TO_YOUR_DATA_FILE2"};

    // Instantiates a client
    try (AutoMlClient client = AutoMlClient.create()) {

      // Get the complete path of the dataset.
      DatasetName datasetFullId = DatasetName.of(projectId, computeRegion, datasetId);

      GcsSource.Builder gcsSource = GcsSource.newBuilder();

      // Get multiple training data files to be imported from gcsSource.
      for (String inputUri : gcsUris) {
        gcsSource.addInputUris(inputUri);
      }

      // Import data from the input URI
      InputConfig inputConfig = InputConfig.newBuilder().setGcsSource(gcsSource).build();
      System.out.println("Processing import...");

      Empty response = client.importDataAsync(datasetFullId, inputConfig).get();
      System.out.println(String.format("Dataset imported. %s", response));
    }
  }
}
// [END automl_natural_language_entity_import_data]