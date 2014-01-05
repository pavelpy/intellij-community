/*
 * Copyright 2000-2014 JetBrains s.r.o.
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
package com.intellij.ide.browsers;

import com.intellij.CommonBundle;
import com.intellij.ide.IdeBundle;
import com.intellij.openapi.util.NullableComputable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.UUID;

import static com.intellij.ide.browsers.BrowsersConfiguration.BrowserFamily;

public abstract class WebBrowser {
  protected @NotNull BrowserFamily family;
  protected @NotNull String name;

  protected WebBrowser(@NotNull BrowserFamily family, @NotNull String name) {
    this.family = family;
    this.name = name;
  }

  @NotNull
  public String getName() {
    return name;
  }

  @NotNull
  public abstract UUID getId();

  @NotNull
  public BrowserFamily getFamily() {
    return family;
  }

  @NotNull
  public abstract Icon getIcon();

  @Nullable
  public abstract String getPath();

  @NotNull
  public String getBrowserNotFoundMessage() {
    return IdeBundle.message("error.0.browser.path.not.specified", getFamily().getName(), CommonBundle.settingsActionPath());
  }

  @Nullable
  public BrowserSpecificSettings getSpecificSettings() {
    return null;
  }

  @NotNull
  public static WebBrowser createCustomBrowser(@NotNull BrowserFamily family,
                                               @NotNull String name,
                                               @NotNull Icon icon,
                                               @NotNull NullableComputable<String> pathComputable,
                                               @Nullable String browserNotFoundMessage) {
    return new CustomWebBrowser(family, name, icon, pathComputable, browserNotFoundMessage);
  }

  @Override
  public String toString() {
    return getName() + " (" + getPath() + ")";
  }
}