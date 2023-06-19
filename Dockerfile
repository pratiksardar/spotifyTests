# Use a base image with Java and Chrome WebDriver installed
FROM openjdk:8

# Set the working directory inside the container
WORKDIR /app

# Copy the test suite files into the container
COPY . .

# Install Chrome and Chrome WebDriver
RUN apt-get update && apt-get install -y curl unzip wget libxi6 libgconf-2-4 \
    && wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - \
    && echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google.list \
    && apt-get update \
    && apt-get install -y google-chrome-stable \
    && LATEST_CHROME_VERSION=$(curl -sS chromedriver.storage.googleapis.com/LATEST_RELEASE) \
    && wget -O /tmp/chromedriver.zip https://chromedriver.storage.googleapis.com/$LATEST_CHROME_VERSION/chromedriver_linux64.zip \
    && unzip /tmp/chromedriver.zip -d /usr/local/bin/ \
    && chmod +x /usr/local/bin/chromedriver

# Set environment variables for Chrome WebDriver
ENV PATH="/usr/local/bin:${PATH}"

# Compile and run the tests
CMD ["mvn", "clean", "test"]