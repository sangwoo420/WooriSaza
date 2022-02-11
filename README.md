## 카테고리

| Application | Domain | Language | Framework |
| ---- | ---- | ---- | ---- |
| :black_square_button: Desktop Web | :black_square_button: AI | :white_check_mark: JavaScript | :white_check_mark: Vue.js |
| :white_check_mark: Mobile Web | :black_square_button: Big Data | :black_square_button: TypeScript | :black_square_button: React |
| :black_square_button: Responsive Web | :black_square_button: Blockchain | :black_square_button: C/C++ | :black_square_button: Angular |
| :black_square_button: Android App | :black_square_button: IoT | :black_square_button: C# | :black_square_button: Node.js |
| :black_square_button: iOS App | :black_square_button: AR/VR/Metaverse | :black_square_button: Python | :white_check_mark: Flask/Django |
| :black_square_button: Desktop App | :black_square_button: Game | :white_check_mark: Java | :white_check_mark: Spring/Springboot |
| | | :black_square_button: Kotlin | |



## 프로젝트 소개

* 프로젝트명: 우리사자
* 서비스 특징 및 주요기능
  - 비슷한 지역에서 공동 구매를 통해 내가 필요한만큼, 더 싸게 구매하기!
  - 돈 관련하여 우리 사이트가 중간에서 물건을 수령하면 돈이 전달되도록 하여 안전성 확보하기!
  - 각 사용자마다 신뢰 등급, 댓글보기, 후기를 통해 사용자의 신뢰성 확인하기!
  - 서드 파티를 통한 계정 관리
  - 공동 구매를 희망하는 사람들끼리 파티를 맺을 수 있도록 플랫폼 제공
* 주요 기술
  - Vue
  - Spring boot
  - JPA
  - Rest API


## 팀 소개
- 김현수 : Front-end(main), 팀장
- 박상우 : Back-end(main)
- 박소진 : Back-end(sub)
- 윤지영 : Full-stack
- 장영하 : Back-end(sub), 부팀장
- 최윤석 : Front-end(sub)



## 프로젝트 상세 설명
// 개발 환경, 기술 스택, 시스템 구성도, ERD, 기능 상세 설명 등





# 공구의 시작, 우리사자 🦁

![icon](.\images\icon.png)

## 목차 

1. 팀소개

2. 프로젝트 소개
   - 홈
   - 이거사자
   - 마이사자
   - 가이드
   - 마이페이지

3. 프로젝트 명세

   - 아키텍처
   - ERD
   - UI 프토로타입
   - Usecase Diagram
   - 기능 명세서

   

## 0. 팀소개 🙋‍♂️

> 팀명 : 수상한 사람들

- 김현수 : Front-end(main), 팀장

- 박상우 : Back-end(main)

- 박소진 : Back-end(sub)

- 윤지영 : Full-stack

- 장영하 : Back-end(sub), 부팀장

- 최윤석 : Front-end(sub)



## 1. 프로젝트 소개 🎁

>내가 원하는 물건을 이웃과 함께 저렴한 가격으로!
>물건을 수령하면 대금이 전달되는 시스템으로 더욱 안전한 거래를 보장합니다!
>공동 구매의 수요가 급증하는 요즘, 우리사자와 함께 공동구매를 시작해보세요 :)



### 1.1 홈

- 사이트 소개와 함께 파티장과 파티원의 간단한 역할과 개념을 설명해줍니다.
- `시작하기` 버튼을 클릭하면 더 자세한 사이트 이용 가이드 페이지로 이동합니다.

![main](.\images\main.gif)



### 1.2 이거사자

- 유저들이 올린 공동구매 게시글을 볼 수 있는 공간입니다.
- 각각의 게시글의 제목, 내용, 가격, 모집 인원 수, 마감 기한을 볼 수 있습니다.
- 카테고리, 지역범위에 따라 필터링해서 게시글을 볼 수 있습니다.
- 검색어를 입력하면 제목 또는 내용에 검색어가 포함된 게시글을 볼 수 있습니다.
- 게시글의 장바구니 버튼을 눌러서 찜을 할 수 있습니다.
- 게시글을 누르면 해당 게시글의 디테일 페이지로 이동합니다.



### 1.3 마이사자

- 해당 유저의 정보, 참가한 파티, 개설한 파티, 댓글, 후기를 볼 수 있는 공간입니다.
- 해당 유저의 등급과, 이름, 주소와 가입날짜가 담긴 카드를 볼 수 있습니다.
  카드는 본인이었을 경우에만 누를 수 있고 누르면 마이페이지로 이동합니다.
- `참가한 파티 ` 탭에서는 해당 유저가 현재 참가하고 있는 파티 리스트를 볼 수 있습니다.
  파티를 누르면 해당 파티의 파티 디테일 페이지로 이동합니다.
- `개설한 파티`  탭에서는 해당 유저가 개설한 파티 리스트를 볼 수 있습니다.
  파티를 누르면 해당 파티의 파티 디테일 페이지로 이동합니다.
- `댓글 ` 탭에서는 해당 유저가 단 댓글 리스트를 볼 수 있습니다. 댓글을 누르면 해당 댓글이 달린 게시글로 이동합니다.
- `후기` 탭에서는 해당 유저에게 달린 후기 리스트를 볼 수 있습니다.



### 1.4 가이드

- 사용자들이 `우리사자`를 쉽게 이용할 수 있도록 가이드를 제공합니다.

![guide](.\images\guide.gif)

### 1.5 마이페이지

- 유저들이 자신의 정보를 수정하고 자신의 찜목록, 1:1문의, 내가 쓴 후기를 볼 수 있는 공간입니다.
- `정보수정` 탭에서는 닉네임과 주소, 프로필 사진을 수정할 수 있습니다.
- `찜목록` 탭에서는 자신이 찜한 게시글 리스트를 볼 수 있습니다.
  게시글을 누르면 해당 게시글 디테일 페이지로 이동합니다.
- `나의 1:1 문의` 탭에서는 `문의하기` 버튼을 눌러서 문의글을 작성할 수 있고, 자신의 문의글 리스트를 볼 수 있습니다.
- `내가 쓴 후기` 탭에서는 자신이 작성한 후기 리스트를 볼 수 있습니다.



## 3. 프로젝트 명세 🛠

### 1.1 아키텍처

![아키텍처](./images/아키텍처.png)

### 1.2 ERD



### 1.3 UI 프로토타입

- https://www.figma.com/file/89ipYukI0aTZRnJ7tCbhpQ/%EC%88%98%EC%83%81%ED%95%9C%EC%82%AC%EB%9E%8C%EB%93%A4-team-library?node-id=0%3A1

![image-20220211135208483](.\images\UI)



### 1.4 Usecase Diagram

- https://app.diagrams.net/#G141ohjTX3GOldM-FH4RdT4x81qykGr-Qv

![수상한사람들-Page-2.drawio](.\images\Usecase)



### 1.5 요구사항 명세서

| 순번 | 요구사항 ID | 대분류                 | 소분류                                | 요구사항 상세                                                | 우선순위 |
| ---- | ----------- | ---------------------- | ------------------------------------- | ------------------------------------------------------------ | -------- |
| 1    | F01-01      | 회원 가입              | 소셜 아이디 회원가입                  | 우측 상단 ‘회원가입’ 버튼을 눌러 소셜 로그인으로 회원 가입을 진행한다. | 상       |
| 2    | F01-02      | 회원 가입              | 추가 정보 입력                        | 닉네임과 주소를 추가 입력한다.                               | 상       |
|      |             |                        |                                       |                                                              |          |
| 3    | F02-01      | 로그인/로그아웃        | 소셜 아이디 로그인                    | 우측 상단 ‘로그인’ 버튼을 눌러 소셜 로그인으로 로그인한다.   | 상       |
| 4    | F02-02      | 로그인/로그아웃        | 회원 정보                             | 소셜 아이디에 해당되는 회원 정보를 불러온다.                 | 상       |
| 5    | F02-03      | 로그인/로그아웃        | 로그아웃                              | 우측 상단 드롭다운에서 ‘로그아웃’을 선택하여 로그아웃한다.   | 상       |
|      |             |                        |                                       |                                                              |          |
| 6    | F03-01      | 메인 페이지(홈)        | 서비스 소개                           | 서비스에 대해서 소개한다.                                    | 상       |
| 7    | F03-02      | 메인 페이지(홈)        | 서비스 사용방법                       | 서비스 사용방법을 확인한다.                                  | 상       |
|      |             |                        |                                       |                                                              |          |
| 8    | F04-01      | 이거사자(파티 리스트)  | 주변 파티 정보 수집                   | 사용자의 선택에 따라 카테고리와 범위를 달리하여 기준 주소 반경의 생성된 파티들의 정보를 불러온다. | 상       |
| 9    | F04-02      | 이거사자(파티 리스트)  | 주변 파티 정보 시각화                 | 불러온 파티 정보들을 리스트로 보여준다                       | 상       |
| 10   | F04-03      | 이거사자(파티 리스트)  | 카테고리, 정렬 기준, 구역 범위 선택   | 카테고리, 정렬 기준, 구역범위를 기준으로 게시판 리스트를 리렌더링한다. | 상       |
| 11   | F04-04      | 이거사자(파티 리스트)  | 파티 참석 여부 시각화                 | 모집 인원, 기간 만료, 참석 여부를 확인하여 시각화(파티 참여중, 마감 태그 부착)하여 보여준다. | 중       |
| 12   | F04-05      | 이거사자(파티 리스트)  | 파티 상세페이지로 이동                | 리스트에서 게시글 클릭 시 해당 게시글 페이지로 이동한다.     | 상       |
| 13   | F04-06      | 이거사자(파티 리스트)  | 글쓰기 , 글 수정                      | 우측 하단의 연필 모양 플로팅 버튼을 클릭하면 글쓰기 페이지로 이동한다. | 상       |
| 14   | F04-07      | 이거사자(파티 리스트)  | 채팅                                  | 우측 하단의 말풍선 모양 플로팅 버튼을 누르면 채팅 목록을 표시한다. | 상       |
|      |             |                        |                                       |                                                              |          |
| 15   | F05-01      | 파티 글 페이지         | 상품 정보 표시                        | 상품 이미지, 상품명, 구매링크, 유효기간, 모집인원, 가격, 글 내용을 표시한다. | 상       |
| 16   | F05-02      | 파티 글 페이지         | 찜 등록                               | 게시글 중앙의 찜(하트) 버튼을 누르면 마이페이지 내 찜목록에서 확인할수 있다. | 중       |
| 17   | F05-03      | 파티 글 페이지         | 댓글 등록                             | 게시글에 댓글을 작성할 수 있다.                              | 상       |
| 18   | F05-04      | 파티 글 페이지         | 파티 참여하기                         | 파티원이 ‘참여하기’ 버튼을 누르면 파티에 참여할 수 있으며, ‘참여하기’ 버튼이 ‘상세보기’ 버튼으로 바뀐다. | 상       |
| 19   | F05-05      | 파티 글 페이지         | 파티 상세보기                         | 파티 참여중인 사용자는 ‘상세보기’ 버튼을 눌러 파티 상세정보 페이지로 이동할 수 있다. | 상       |
|      |             |                        |                                       |                                                              |          |
| 20   | F06-01      | 글쓰기 페이지          | 글 작성                               | 글 제목, 상품명, 구매링크, 글 내용을 작성할 수 있다.         | 상       |
| 21   | F06-02      | 글쓰기 페이지          | 사진 등록                             | 하단의 ‘+’ 버튼을 클릭하여 사진을 추가할 수 있다.            | 상       |
| 22   | F06-03      | 글쓰기 페이지          | 정보 등록                             | 카테고리, 구매 인원, 총 금액, 유효 날짜를 등록할 수 있다.    | 상       |
| 23   | F06-04      | 글쓰기 페이지          | 작성 완료                             | ‘작성 완료’ 버튼을 누르면 글이 작성되고 파티 글 페이지로 이동한다. | 상       |
| 24   | F06-05      | 글쓰기 페이지          | 작성 취소                             | ‘작성 취소’ 버튼을 누르면 파티 목록(이거사자)으로 이동한다.  | 상       |
|      |             |                        |                                       |                                                              |          |
| 25   | F07-01      | 참여신청서             | 참여신청서 정보 확인                  | 참여하고자 하는 파티의 제품명, 기간, 인원을 확인할 수 있다.  | 상       |
| 26   | F07-02      | 참여신청서             | 구매 수량 선택                        | 구매하려는 수량을 선택하면 가격이 계산되어 사용자에게 보여진다. | 상       |
| 27   | F07-03      | 참여신청서             | 유의사항 확인 및 동의                 | 유의사항을 확인하고 사용자의 동의를 얻는다.                  | 상       |
| 28   | F07-04      | 참여신청서             | 정산하기                              | ‘정산하기’ 버튼을 누르면 결제 페이지로 이동한다.             | 상       |
|      |             |                        |                                       |                                                              |          |
| 29   | F08-01      | 정산하기               | 결제 정보 확인                        | 구매하는 제품의 정보와 금액을 확인할 수 있다.                | 중       |
| 30   | F08-02      | 정산하기               | 결제 수단 선택                        | 결제 수단을 선택하고 수단에 따라 입력 형식이 바뀐다.         | 중       |
| 31   | F08-03      | 정산하기               | 결제                                  | 입력 형식을 충족하면 ‘결제하기’ 버튼을 눌러 결제를 진행한다. | 중       |
|      |             |                        |                                       |                                                              |          |
| 32   | F09-01      | 정산 완료              | 정산완료 및 상세정보 보기 버튼 활성화 | 정산이 완료되면 확인 메세지와 함께 ‘상세정보 보기’ 버튼이 활성화 된다. | 상       |
| 33   | F09-02      | 정산 완료              | 상세보기 버튼                         | ‘상세보기’ 버튼을 누르면 내가 구매한 제품의 상세정보 페이지로 이동한다. | 상       |
|      |             |                        |                                       |                                                              |          |
| 34   | F10-01      | 정산 상세정보          | 정보 확인                             | 파티 글 정보와 내가 구매한 수량, 금액을 확인할 수 있다.      | 상       |
| 35   | F10-02      | 정산 상세정보          | 파티원 정보                           | 파티원 정보를 카드형식으로 확인할 수 있다.                   | 상       |
|      |             |                        |                                       |                                                              |          |
| 36   | F11-01      | 정산 상세정보(마감 전) | 파티장 - 구매 진행                    | ‘파티마감하고 구매 진행하기’ 버튼을 누르면 상세정보 - 구매진행중 페이지로 이동한다. | 상       |
| 37   | F11-02      | 정산 상세정보(마감 전) | 파티장 - 파티 삭제하기                | 파티 삭제하기 버튼을 누르면 파티가 삭제된다.                 | 상       |
| 38   | F11-03      | 정산 상세정보(마감 전) | 파티장 - 전체 채팅방 생성             | 파티마감하고 구매 진행하기 버튼을 누르면 전체 파티원을 대상으로 채팅방이 생성 및 활성화된다. | 상       |
| 39   | F11-04      | 정산 상세정보(마감 전) | 파티원 정보                           | 파티원 정보를 카드형식으로 확인할 수 있다.                   | 상       |
| 40   | F11-05      | 정산 상세정보(마감 전) | 파티원 - 참가 취소                    | 파티원은 파티 마감 전까지 ‘참가 취소하기’ 버튼을 눌러 파티를 나갈 수 있다. | 상       |
|      |             |                        |                                       |                                                              |          |
| 41   | F12-01      | 정산 상세정보(마감 후) | 파티장 - 구매 인증 폼 등록하기        | ‘구매 인증 폼 등록하기’ 버튼을 누르면 물건을 구매한 것을 증명하기 위한 구매 인증 폼으로 이동한다. | 상       |
| 42   | F12-02      | 정산 상세정보(마감 후) | 파티원 - 버튼 활성화                  | 마감 후에는 ‘구매 인증 폼 확인하기’ 버튼, 상품 미 수령을 대비한 ‘물건을 못받았어요’ 버튼, ‘구매 확정하기’ 버튼이 활성화된다. | 상       |
| 43   | F12-03      | 정산 상세정보(마감 후) | 파티원 - 구매 인증 확인               | ‘구매 인증 폼 확인하기’ 버튼을 누르면 파티장이 업로드한 구매 인증 폼을 확인할 수 있다. | 상       |
| 44   | F12-04      | 정산 상세정보(마감 후) | 파티원 - 상품 미수령                  | 상품 미수령 시 ‘물건을 못받았어요’ 버튼을 누르면 1대1 문의 페이지로 이동한다. | 상       |
| 45   | F12-05      | 정산 상세정보(마감 후) | 파티원 - 구매 확정                    | ‘구매 확정하기’ 버튼을 누르면 파티장에게 대금이 이체되고 후기 남기기 페이지로 이동한다. | 상       |
|      |             |                        |                                       |                                                              |          |
| 46   | F13-01      | 구매 인증 폼           | 인증 사진 등록                        | 내가 구매했다는 것을 인증할 수 있는 사진을 등록한다.         | 상       |
| 47   | F13-02      | 구매 인증 폼           | 운송장번호 등록                       | 파티장은 구매한 상품의 운송장번호를 등록한다.                | 상       |
| 48   | F13-03      | 구매 인증 폼           | 예상일 등록                           | 택배 예상 도착일과 파티원과 조율한 예상 수령일을 등록한다.   | 상       |
| 49   | F13-04      | 구매 인증 폼           | 폼 등록하기                           | ‘구매 인증 폼 등록하기’ 버튼을 누르면 등록이 완료되며, 파티원의 상세정보 페이지에서 구매 인증 폼을 확인할 수 있다. | 상       |
|      |             |                        |                                       |                                                              |          |
| 50   | F14-01      | 후기 남기기 페이지     | 회원 정보 확인                        | 나와 함께 구매한 회원의 등급,닉네임을 확인할 수 있다.        | 중       |
| 51   | F14-02      | 후기 남기기 페이지     | 후기 등록하기                         | 텍스트 상자에 후기를 남긴다.                                 | 중       |
| 52   | F14-03      | 후기 남기기 페이지     | 별점 설정하기                         | 상대방에게 별점을 남긴다.                                    | 중       |
| 53   | F14-04      | 후기 남기기 페이지     | 작성 스킵                             | ‘나중에 하기’ 버튼을 누르면 후기작성을 스킵할 수 있다.       | 중       |
| 54   | F14-05      | 후기 남기기 페이지     | 작성 완료                             | ‘작성 완료’ 버튼을 누르면 후기가 작성되며, 해당  마이사자 페이지에서 후기를 확인할 수 있다. | 중       |
|      |             |                        |                                       |                                                              |          |
| 55   | F15-01      | 마이사자(프로필)       | 개설/참가한 파티                      | 매칭여부 글 제목, 기간, 모집인원, 총 금액, 나누어진 금액을 보여준다. | 상       |
| 56   | F15-02      | 마이사자(프로필)       | 파티 상세 정보로 이동                 | 참가한/개설한 파티의 게시글을 누르면 파티 상세 페이지로 이동한다. | 상       |
| 57   | F15-03      | 마이사자(프로필)       | 댓글                                  | 내가 단 글의 제목과 내가 작성한 댓글 내용, 날짜를 보여준다.  | 상       |
| 58   | F15-04      | 마이사자(프로필)       | 파티글로 이동                         | 내가 단 댓글 리스트에서 댓글 카드를 클릭하면 해당 댓글이 달린 파티글로 이동한다. | 상       |
| 59   | F15-05      | 마이사자(프로필)       | 후기                                  | 후기를 남긴 상대방의 닉네임과 후기 내용, 날짜를 보여준다.    | 중       |
| 60   | F15-06      | 마이사자(프로필)       | 마이페이지로 이동                     | 화면 상단의 신분증 카드를 누르면 마이페이지로 이동한다.      | 상       |
|      |             |                        |                                       |                                                              |          |
| 61   | F16-01      | 마이페이지             | 정보 수정                             | 닉네임, 주소, 프로필 사진 정보를 수정할 수 있다.             | 상       |
| 62   | F16-02      | 마이페이지             | 나의 1:1 문의 리스트                  | 내가 남긴 1:1 문의 리스트를 보여준다.                        | 중       |
| 63   | F16-03      | 마이페이지             | 1:1 문의 작성                         | 문의 작성 버튼을 누르면 1:1 문의 작성 페이지로 이동한다.     | 중       |
| 64   | F16-04      | 마이페이지             | 1:1 문의 답변                         | 답변글이 달리면 1:1 문의 답변을 확인할 수 있다.              | 중       |
| 65   | F16-05      | 마이페이지             | 신분증 카드                           | 닉네임, 등급, 주소, 가입일 정보를 보여준다.                  | 상       |
| 66   | F16-06      | 마이페이지             | 찜목록                                | 내가 찜한 파티 리스트를 보여준다.                            | 상       |
|      |             |                        |                                       |                                                              |          |
| 67   | F17-01      | 가이드                 | FAQ / 서비스 이용법                   | 서비스 이용 방법에 대한 설명과 자주 묻는 질문에 대한 대답을 적어 놓는다. | 중       |
|      |             |                        |                                       |                                                              |          |
| 68   | F18-01      | 채팅                   | 채팅방 리스트                         | 현재 내가 참가하고 있는 채팅방을 보여준다.                   | 상       |
| 69   | F18-02      | 채팅                   | 채팅하기                              | 채팅방을 클릭하여 들어가면 현재까지의 채팅글이 보이고, 채팅 글을 입력할 수 있다. | 상       |
| 70   | F18-03      | 채팅                   | 채팅방 나가기                         | 채팅방 나가기 버튼을 누르면 채팅방에서 나가지고 채팅방 리스트에서도 삭제된다. | 상       |
