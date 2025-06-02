/*
 * Copyright 2025 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.ai.edge.gallery

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.google.ai.edge.gallery.data.AppContainer
import com.google.ai.edge.gallery.data.DefaultAppContainer
import com.google.ai.edge.gallery.ui.common.writeLaunchInfo
import com.google.ai.edge.gallery.ui.theme.ThemeSettings

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "app_gallery_preferences")

class GalleryApplication : Application() {
  /** AppContainer instance used by the rest of classes to obtain dependencies */
  lateinit var container: AppContainer

  override fun onCreate() {
    super.onCreate()


    writeLaunchInfo(context = this)
    container = DefaultAppContainer(this, dataStore)

    // Load theme.
    ThemeSettings.themeOverride.value = container.dataStoreRepository.readThemeOverride()
  }
}