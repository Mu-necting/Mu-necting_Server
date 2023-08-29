# 🎵Mu:necting-음악으로 연결하다_Spring boot🍀
<br/>

## 📆 프로젝트 일정
**집중 개발 기간** : 2023-7/10 ~ 8/24 <br/>
**유지보수 기간** : 9/1 ~

## 🫂팀원

<table>
  <tbody>
    <tr>
      <td align="center"><a href="https://github.com/kchaeys2"><img src="https://avatars.githubusercontent.com/u/106591445?v=4" width="150px;" alt=""/><br /><b>김채연</b></a><br /></td>
      <td align="center"><a href="https://github.com/tkamo2006"><img src="https://avatars.githubusercontent.com/u/101330766?v=4" width="150px;" alt=""/><br /><b>한유성</b></a><br /></td>
      <td align="center"><a href="https://github.com/hysong4u"><img src="https://avatars.githubusercontent.com/u/102026726?v=4" width="150px;" alt=""/><br /><b>송하연</b>
</a><br /></td>
     <tr/>
      <td align="center">Backend</td>
      <td align="center">Backend</td>
      <td align="center">Backend</td>
    </tr>
  </tbody>
</table> 

### [📈api sheet](https://excessive-william-d4d.notion.site/API-2341dc4e561c477b94b68aecede325e4?pvs=4)


## 🐈기술 스택

<br/>

**Framework**
- Java 17
- Spring Boot 3.1.1
  
**Dependencies**

- Spring Security + JWT
- Spring Data Jpa
- QueryDsl
- JUnit 5

**Infra**
- Github Actions
- AWS EC2
- AWS S3
- AWS CodeDeploy
- AWS RDS

**Database**
- MySQL (Local, Deploy DB)

## 📁 Package Structure
- main
  - java
    - com.munecting.server
      - domain
        - archive 
          - entity
          - repository
          - service
          - controller
          - dto
        - member
          <br/> ``
        - music
          <br/> ``
        - pick
          <br/> ``
        - reply
          <br/> ``
      - global
        - config
          -secure
        - utils
          - spotfy
            
  - resources
    - application.yml

<br/>

## 📋 ERD
<img width="589" alt="erd" src="https://github.com/Mu-necting/Mu-necting_Server/assets/106591445/f5461568-9589-488c-910d-4aa5ce31ec3a">

## 🔧 소프트웨어 아키텍처

![munecting다이어그램](https://github.com/Mu-necting/Mu-necting_Server/assets/106591445/12ec0f59-0825-4094-8d48-8d5bf7d8bf2c)


## 🌊 Git Flow
- munecting : 배포
- develop : 개발
- feature#이슈 번호 : 새로운 기능 추가
- release#이슈 번호 : 배포전 최종 버그 수정
- hotfix#이슈 번호 : 배포 버전의 버그 수정

<br/>

## 🤝 Commit Convention

|태그 이름|설명|
|---------|-----------------|
|`FEAT`|새로운 기능을 추가할 경우|
|`FIX`|버그를 고친 경우|
|`!BREAKING CHANGE`|커다란 API 변경의 경우|
|`!HOTFIX`|급하게 치명적인 버그를 고쳐야하는 경우|
|`STYLE`|코드 포맷 변경,세미 콜론 누락,코드 수정이 없는 경우|
|`REFACTOR`|프로덕션 코드 리펙토링|
|`COMMENT`|필요한 주석 추가 및 변경|
|`Docs`|문서를 수정한 경우|
|`TEST`|테스트 추가,테스트 리펙토링(프로덕션 코드 변경X)|
|`CHORE`|빌드 테스트 업데이트,패키지 매니저를 설정하는 경우(프로덕션 코드 변경 X)|
|`RENAME`|파일 혹은 폴더명을 수정하거나 옮기는 작업만인 경우|
|`REMOVE`|파일을 삭제하는 작업만 수행한 경우|

`ex)[FEAT] 설명_추가설명#이슈번호`







