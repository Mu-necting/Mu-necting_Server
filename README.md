# 🎵Mu:necting-음악으로 연결하다_Spring boot🍀
<br/>

## Package Structure
- main
  - java
    - com.munecting.server
      - domain
        - archive 
          - entity 
        - member
          - entity
        - music
          - entity
      - global
        - config
        - utils
            
  - resources
    - application.yml

<br/>

## Branch Structure
- main : 배포
- develop : 개발
- feature#기능 : 새로운 기능 추가
- release#버 : 배포전 최종 버그 수정
- hotfix#버그 : 배포 버전의 버그 수정

<br/>

## NAMING RULE

|태그 이름|설명|
|---------|-----------------|
|FEAT|새로운 기능을 추가할 경우|
|FIX|버그를 고친 경우|
|!BREAKING CHANGE|커다란 API 변경의 경우|
|!HOTFIX|급하게 치명적인 버그를 고쳐야하는 경우|
|STYLE|코드 포맷 변경,세미 콜론 누락,코드 수정이 없는 경우|
|REFACTOR|프로덕션 코드 리펙토링|
|COMMENT|필요한 주석 추가 및 변경|
|Docs|문서를 수정한 경우|
|TEST|테스트 추가,테스트 리펙토링(프로덕션 코드 변경X)|
|CHORE|빌드 테스트 업데이트,패키지 매니저를 설정하는 경우(프로덕션 코드 변경 X)|
|RENAME|파일 혹은 폴더명을 수정하거나 옮기는 작업만인 경우|
|REMOVE|파일을 삭제하는 작업만 수행한 경우|









