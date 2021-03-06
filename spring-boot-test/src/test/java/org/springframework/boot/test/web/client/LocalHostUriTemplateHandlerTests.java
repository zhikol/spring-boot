/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot.test.web.client;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import org.springframework.mock.env.MockEnvironment;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link LocalHostUriTemplateHandler}.
 *
 * @author Phillip Webb
 */
public class LocalHostUriTemplateHandlerTests {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void createWhenEnvironmentIsNullShouldThrowException() {
		this.thrown.expect(IllegalArgumentException.class);
		this.thrown.expectMessage("Environment must not be null");
		new LocalHostUriTemplateHandler(null);
	}

	@Test
	public void getBaseUrlShouldUseLocalServerPort() throws Exception {
		MockEnvironment environment = new MockEnvironment();
		environment.setProperty("local.server.port", "1234");
		LocalHostUriTemplateHandler handler = new LocalHostUriTemplateHandler(
				environment);
		assertThat(handler.getRootUri()).isEqualTo("http://localhost:1234");
	}

	@Test
	public void getBaseUrlWhenLocalServerPortMissingShouldUsePort8080() throws Exception {
		MockEnvironment environment = new MockEnvironment();
		LocalHostUriTemplateHandler handler = new LocalHostUriTemplateHandler(
				environment);
		assertThat(handler.getRootUri()).isEqualTo("http://localhost:8080");
	}

}
