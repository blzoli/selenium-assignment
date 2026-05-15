# Runs the Selenium JUnit tests inside a container.
# Requires a Selenium server reachable via SELENIUM_REMOTE_URL.

FROM gradle:8.7-jdk17

USER root
RUN apt-get update \
    && apt-get install -y --no-install-recommends curl \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /home/gradle/project

# WORKDIR is created as root; ensure gradle user can write build caches
RUN chown -R gradle:gradle /home/gradle/project

# Copy only build files first for better Docker layer caching
COPY --chown=gradle:gradle build.gradle ./

# Copy sources
COPY --chown=gradle:gradle src ./src

# Add entrypoint (waits for Selenium)
COPY docker/entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh \
    && chown gradle:gradle /entrypoint.sh

USER gradle

# Pre-download dependencies
RUN gradle testClasses --no-daemon

ENV SELENIUM_REMOTE_URL=http://selenium:4444/wd/hub

ENTRYPOINT ["/entrypoint.sh"]
CMD ["gradle", "test", "--no-daemon"]
