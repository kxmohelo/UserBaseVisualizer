#UserBaseVisualiser

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

A CLI application target for fast-growing companies that would like to visualise the growth of their 
user-base. They can do this by running a terminal command that draws a graphs in the terminal. This
is done by retrieving an API endpoint, that outputs the data to be visualised - graphed.

<p align="right">(<a href="#top">back to top</a>)</p>



### Built With

These are the list of frameworks/libraries used to bootstrap the project:

* [Java 11](https://www.java.com/)
* [Picocli](https://picocli.info/)
* [Jetty](https://www.eclipse.org/jetty/)
* [JUnit 5](https://junit.org/junit5/)
* [Maven](https://maven.apache.org/)


<p align="right">(<a href="#top">back to top</a>)</p>



<!-- GETTING STARTED -->
## Getting Started

This is an example of how you may give instructions on setting up your project locally.
To get a local copy up and running follow these simple example steps.

### Prerequisites

A list things you need to use the software and how to install them:
* java
  ```sh
  sudo apt install default-jdk
  ```

* maven
  ```sh
  sudo apt install maven
  ```

### Installation

* Click this [link](https://java.com/en/download/help/download_options.html) for instructions on how to install java:

* The following are instructions on how to install java:

1. Start by updating the package index:
 ```sh
  sudo apt update
  ```
2. Next, install Maven by typing the following command:
   ```sh
   sudo apt install maven
   ```
3. Verify the installation by running the mvn -version command:
   ```sh
   mvn -version
   ```
   
### Packaging & Maven Lifecycles

In your command-line, go to the project directory and use following maven command to generate the ubv-jar-with-dependencies.jar file. 

You will find the .jar file in the '/target' folder inside the project directory. 

* ```sh
  mvn clean package
  ```
Other maven command you can use for various checks, must still be in the project directory

* ```sh
  mvn clean
  ```

2. Validate the project is correct and all necessary information is available
* ```sh
  mvn validate
  ```
  
3. Compile the source code of the project
* ```sh
  mvn compile
  ```

4. Test the compiled source code using a suitable unit testing framework. These tests should not require the code be packaged or deployed
* ```sh
  mvn test
  ```
5. Take the compiled code and package it in its distributable format, such as a JAR.
* ```sh
  mvn package
  ```



### Setup

Follow the instructions below to set up ubv (User Base Visualiser)

* #### Linux setup:

1. In your terminal, open ~/.bashrc as follows:
    ```sh
    vi ~/.bashrc
    ```
   
2. Paste the following in ~/.bashrc and save
   ```sh
   alias ubv = 'java -jar path/to/ubv-jar-with-dependencies.jar'
   ```

3. Source ~/.bashrc as follows
   ```sh
   source ~/.bashrc
   ```
   
4. Restart your terminal, then you can use ubv as follows:
   ```sh
   ubv -h or ubv [-hV] -t=<targetPath> [-p=<"dd-MM-yyyy"> [<"dd-MM-yyyy">]]...
   ```
   
* #### Windows setup:

1. Create a file named ubv.cmd:

2. Paste the following line in ubv.cmd
    ```sh
    @java -jar C:\path\to\sayHello.jar %*
    ```

3. Open cmd prompt, and you can use ubv as follows
   ```sh
   ubv -h or ubv [-hV] -t=<targetPath> [-p=<"dd-MM-yyyy"> [<"dd-MM-yyyy">]]...
   ```

<p align="right">(<a href="#top">back to top</a>)</p>


<!-- USAGE EXAMPLES -->
## Usage

* #### UserBaseVisusaliser usage
    ```sh
    ubv [-hV] -t=<targetPath> [-p=<"dd-MM-yyyy"> [<"dd-MM-yyyy">]]...
    ```
* #### Flags
    ```sh
    -p, --period=<"dd-MM-yyyy"> [<"dd-MM-yyyy">] The date or dates used to filter the data by
    -t, --target=<targetPath> The file whose data to graph
    -h, --help      Show this help message and exit.
    -V, --version   Print version information and exit.
    ```

* #### Examples

1. #### -t Flag
    This following will display the graph of all the data.

    ```sh
    ubv -t 'endpoint'
    ```
   
   
2. #### -p Flag
     This following will display the graph of the data filtered by one date.

    ```sh
    ubv -t 'endpoint' -p 'dd-MM-yy'
    ```

3. #### -p Flag
   This following will display the graph of the data filtered by a period between two dates.

    ```sh
    ubv -t 'endpoint' -p 'dd-MM-yy' 'dd-MM-yy'
    ```


<!-- CONTACT -->
## Contact

Kamohelo Mohlabula - [kamomohlabula@icloud.com](https://twitter.com/your_username)

<p align="right">(<a href="#top">back to top</a>)</p>
