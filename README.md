# Gender-detection
Small application that can detect gender by given name

## Technologies
Project is created in: Java 11, Spring 2.3.4, Gradle 6.8.2, Junit 4.13, Hamcrest 2.2, Mockito 3.8.0, IntelliJ IDEA 11.0.7, Git

## API REST simplified Documentation:
paths: /api/gender
  /1:
    get:
      summary: guess gender with first variant
      parameters:
        - name: name
          description: Put a name and detect its gender (only first part of name is taking into consideration)
          required: false
          type: String
      responses:
        '200':
          description: A paged with information of gender (male, female or inconclusive)
  /2: 
      get:
        summary: guess gender with second variant
        parameters:
          - name: name
            description: Put a name and detect its gender (whole name is taking into consideration)
            required: false
            type: String
        responses:
          '200':
            description: A paged with information of gender (male, female or inconclusive)
  /tokens/male: 
      get:
        summary: lists all male
        responses:
          '200':
            description: A paged with all male names
 /tokens/female: 
      get:
        summary: lists all female
        responses:
          '200':
            description: A paged with all female names
 /tokens/people: 
      get:
        summary: lists all people
        responses:
          '200':
            description: A paged with all people regardless of gender
            
## Information
App has two flat-files within jar: males.ods, females.ods. Data are derived from files or are created in People class. There is a docker image to pull from docker hub. 
You can do it quickly by 'docker pull tomek563/gender-detection:initial'
