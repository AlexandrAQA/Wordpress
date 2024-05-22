# Используем базовый образ Ubuntu
FROM ubuntu:22.04

# Устанавливаем необходимые пакеты и зависимости
RUN apt-get update && apt-get install -y \
    wget \
    curl \
    gnupg \
    unzip \
    git \
    maven \
    xvfb \
    fonts-liberation \
    libappindicator3-1 \
    libasound2 \
    libatk-bridge2.0-0 \
    libatk1.0-0 \
    libcairo2 \
    libdbus-1-3 \
    libdrm2 \
    libgbm1 \
    libgdk-pixbuf2.0-0 \
    libgtk-3-0 \
    libnspr4 \
    libnss3 \
    libpango-1.0-0 \
    libxcomposite1 \
    libxdamage1 \
    libxrandr2 \
    xdg-utils \
    && rm -rf /var/lib/apt/lists/*

# Скачиваем и устанавливаем Google Chrome версии 113
RUN wget -q -O google-chrome-stable_current_amd64.deb https://dl.google.com/linux/direct/google-chrome-stable_113.0.5672.127-1_amd64.deb \
    && dpkg -i google-chrome-stable_current_amd64.deb || apt-get install -f -y \
    && rm google-chrome-stable_current_amd64.deb

# Скачиваем и устанавливаем ChromeDriver версии 113
RUN wget -q -O chromedriver_linux64.zip https://chromedriver.storage.googleapis.com/113.0.5672.63/chromedriver_linux64.zip \
    && unzip chromedriver_linux64.zip -d /usr/local/bin \
    && rm chromedriver_linux64.zip

# Скачиваем и устанавливаем OpenJDK 22
RUN wget -q -O jdk-22.tar.gz https://download.oracle.com/java/22/latest/jdk-22_linux-x64_bin.tar.gz \
    && mkdir /usr/local/openjdk-22 \
    && tar -xzf jdk-22.tar.gz -C /usr/local/openjdk-22 --strip-components=1 \
    && rm jdk-22.tar.gz

# Устанавливаем JAVA_HOME и PATH
ENV JAVA_HOME=/usr/local/openjdk-22
ENV PATH=$JAVA_HOME/bin:$PATH

# Проверяем версии Java, Maven, Chrome и ChromeDriver
RUN java -version
RUN mvn -version
RUN google-chrome --version
RUN chromedriver --version

# Устанавливаем рабочую директорию в контейнере
WORKDIR /app

# Копируем файл pom.xml и скачиваем зависимости
COPY pom.xml /app/
RUN mvn dependency:go-offline

# Копируем весь проект в рабочую директорию контейнера
COPY . /app

# Компилируем и запускаем тесты с использованием Xvfb
CMD ["sh", "-c", "Xvfb :99 -ac & export DISPLAY=:99 && mvn clean test"]
