# 구현할 기능 목록

> 요구사항에 명시되지 않은 정보여서 추가된 정보의 경우, 문장 맨앞에 ⭐️ 로 표시한다.

<br>

### 1️⃣ 고객은 방문 날짜를 입력한다.
- 방문 날짜는 1 이상 31 이하의 숫자만 입력가능하다.
- 1 이상 31 이하의 숫자가 아닌 경우, 에러 메세지를 출력하고 다시 입력을 받아야한다.

<br>

### 2️⃣ 고객은 주문 매뉴를 입력한다.
- 주문 가능한 매뉴는 아래와 같다.
  ```text
  <애피타이저>
  양송이수프(6,000), 타파스(5,500), 시저샐러드(8,000)

  <메인>
  티본스테이크(55,000), 바비큐립(54,000), 해산물파스타(35,000), 크리스마스파스타(25,000)

  <디저트>
  초코케이크(15,000), 아이스크림(5,000)

  <음료>
  제로콜라(3,000), 레드와인(60,000), 샴페인(25,000)
  ``` 
- 한번에 매뉴 20개 까지 주문할 수 있다.
- 음료만 주문 시, 주문할 수 없다.
- 최소 1개 이상의 매뉴를 주문해야한다. 
- 고객이 메뉴판에 없는 메뉴를 입력하는 경우 에러 메세지를 출력하고 다시 입력을 받아야한다.
- 매뉴 입력 형식이 지정한 양식과 다를 경우 에러 메세지를 출력하고 다시 입력을 받아야한다. 입력형식은 아래와 같다.
  ```text
  해산물파스타-2,레드와인-1,초코케이크-1
  
  //or
  
  타파스-1,제로콜라-1 
  ```

<br>

### 3️⃣ 고객의 주문 내역을 출력한다.
- ⭐️ 주문 매뉴는 `메인` - `사이드` - `에피타이저` - `음료` 순으로 출력한다.
- `<주문 메뉴>` 말머리를 출력한 후 다음 줄부터 주문 매뉴들과 주문 수량을 한줄에 하나씩 출력한다.
- 주문 내역을 출력하기 전에 공백 한줄을 출력한다.
- 출력 양식은 아래와 같다.
  ```text
  
  <주문 메뉴>
  티본스테이크 1개
  바비큐립 1개
  초코케이크 2개
  제로콜라 1개
  ``` 



<br>

### 4️⃣ 고객의 총 주문 금액을 출력한다.
- 1000 원 단위로 `,` 를 붙여서 출력해야한다.
- `<할인 전 총주문 금액>` 말머리를 출력한 후 다음 줄에 총 주문 금액을 출력한다.
- 총 주문 금액을 출력하기 전에 공백 한줄을 출력한다.
- 출력 양식은 아래와 같다.
  ```text
   
  <할인 전 총주문 금액>
  142,000원
  ``` 


<br>

### 5️⃣ 고객의 증정 매뉴를 출력한다.
- 고객의 방문 날짜가 1일 ~ 31일 사이이며 할인 전 총주문 금액이 12만 원 이상일 때, 샴페인 1개를 증정한다.
- ⭐️ 한번의 방문에 최대 1개의 샴페인만 증정할 수 있다.
- 증정 이벤트에 해당하지 않는 경우, `없음` 을 출력한다.
- `<증정 메뉴>` 말머리를 출력한 후 다음 줄에 증정품과 증정 수량을 출력한다.
- 증정 메뉴를 출력하기 전에 공백 한줄을 출력한다.
- 출력 양식은 아래와 같다.
  ```text
  
  <증정 메뉴>
  샴페인 1개
  ``` 
 

<br>

### 6️⃣ 고객의 혜택 내역을 출력한다.
- 고객이 받을 수 있는 혜택은 다음과 같다.

    #### 평일 할인(일요일~목요일)
    - 평일에는 **디저트 메뉴를 메뉴 1개당 2,023원 할인**
    #### 주말 할인(금요일, 토요일)
    - 주말에는 **메인 메뉴를 메뉴 1개당 2,023원 할인** 
    #### 특별 할인
    - 매주 일요일, 크리스마스에 해당 (3, 10, 17, 24, 25, 31)
    - **총주문 금액에서 1,000원 할인**
    #### 크리스마스 디데이 할인
    - 1일 ~ 25일 까지 진행
    - 1000원 으로 시작해서 매일 100원씩 증가해서 3400원 까지 증가
    - **총주문 금액에서 해당 금액만큼 할인**
    #### 증정 이벤트
    - **할인 전 총주문 금액이 12만 원 이상일 때**, 샴페인 1개 증정

    크리스마스 디데이 할인을 제외한 이벤트는 1일 ~ 31일 까지 적용


- 고객에게 적용된 이벤트 내역만 출력해야한다.
- 적용된 이벤트가 하나도 없다면 혜택 내역 `없음` 을 출력한다.
- ⭐️ 이벤트 내역은 `평일` or `주말` - `특별` - `크리스마스` - `증정` 순으로 출력한다.  
- `<혜택 내역>` 말머리를 출력한 후 다음 줄부터 이벤트 내역과 할인한 금액을 출력한다.
- 혜택 내역을 출력하기 전에 공백 한줄을 출력한다.
- 출력 양식은 아래와 같다.
  ```text
  
  <혜택 내역>
  크리스마스 디데이 할인: -1,200원
  평일 할인: -4,046원
  특별 할인: -1,000원
  증정 이벤트: -25,000원
  ```


<br>

### 7️⃣ 고객의 총혜택 금액을 출력한다.
- 6️⃣ 에서 출력한 혜택 내역의 모든 할인 금액을 합친 금액을 출력한다.
  - 총혜택 금액은 `할인 금액의 합계 + 증정 메뉴의 가격` 이다. 
- `<총혜택 금액>` 말머리를 출력한 후 다음 줄에 총혜택 금액을 출력한다. 
- 출력 양식은 아래와 같다.
  ```text
  <총혜택 금액>
  -31,246원
  ``` 

<br>

### 8️⃣ 고객의 할인 후 예상 결제 금액을 출력한다.
- 4️⃣의 금액에서 증정 매뉴의 가격을 제외한 7️⃣의 금액을 뺸 가격을 출력한다.
- `<총혜택 금액>` 말머리를 출력한 후 다음 줄에 할인 후 예상 결제 금액을 출력한다.
- 총혜택 금액을 출력하기 전에 공백 한줄을 출력한다.
- 출력 양식과 계산 예시는 아래와 같다.
    ```text
    <할인 전 총주문 금액>
    142,000원
    
    //...
    
    <총혜택 금액>
    -31,246원
    
    <할인 후 예상 결제 금액>
    135,754원              //142000 - (31,246 - 25000) = 135754 
    ```

<br>

### 9️⃣ 고객의 이벤트 배지를 출력한다.
- 7️⃣ 의 금액에 따라 2024 새해 이벤트에서 활용할 이벤트 배지를 부여한다.
  - 증정 매뉴의 가격을 포함한 금액으로 계산한다.  
- 금액에 따른 이벤트 배지 부여 기준은 다음과 같다. 
  - `5천 원 이상` : 별
  - `1만 원 이상` : 트리
  - `2만 원 이상` : 산타 
- `<12월 이벤트 배지>` 를 출력한 후 다음 줄에 이벤트 배지를 출력한다.
- 이벤트 배지를 출력하기 전에 공백 한줄을 출력한다.
- 출력 양식은 아래와 같다.
  ```text
  <12월 이벤트 배지>
  산타
  ``` 

<br>

### 🔟 안내 메세지를 출력한다.

- #### 1️⃣ 에서 방문 날짜를 입력하기 전에 안내 메세지를 출력한다.
  ```text
  안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.
  12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)
  3                                                    //사용자가 입력하는 부분
  ``` 


- #### 2️⃣ 에서 매뉴를 입력하기 전에 안내 메세지를 출력한다.
  ```text
  주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)
  티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1              //사용자가 입력하는 부분
  ```
  

- #### 3️⃣ 에서 주문 내역을 출력하기 전에 안내 메세지를 출력한다.
  ```text
  12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!
  ```


- ### 1️⃣1️⃣ 에러메세지를 출력한다.
  - 모든 에러메세지는 `[ERROR]` 로 시작해야한다.
  - #### 1️⃣ 에서 1 이상 31 이하의 숫자가 아닌 경우, 아래의 에러 메세지를 출력한다. 
    ```text
    [ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.
    ```
  - #### 2️⃣ 에서 유효하지 않은 매뉴 입력시 에러 메세지를 출력한다.
    - 고객이 메뉴판에 없는 메뉴를 입력하는 경우 , 아래의 에러 메세지를 출력한다.
      ```text
      [ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.
      ``` 
    - 메뉴 형식이 예시와 다른 경우, 아래의 에러 메세지를 출력한다.
      ```text
      [ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.
      ```
    - 중복 메뉴를 입력한 경우, 아래의 에러 메세지를 출력한다.
      ```text
      [ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.
      ```
    - ⭐️ 주문한 음식의 갯수가 20개를 넘을 경우, 아래 매세지를 출력한다.
      ```text
      [ERROR] 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다. 다시 입력해 주세요.
      ```

<br>

### 📍이벤트 플래너 실행 결과 예시
```text
안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.
12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)
3
주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)
티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1
12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!
 
<주문 메뉴>
티본스테이크 1개
바비큐립 1개
초코케이크 2개
제로콜라 1개
 
<할인 전 총주문 금액>
142,000원
 
<증정 메뉴>
샴페인 1개
 
<혜택 내역>
크리스마스 디데이 할인: -1,200원
평일 할인: -4,046원
특별 할인: -1,000원
증정 이벤트: -25,000원
 
<총혜택 금액>
-31,246원
 
<할인 후 예상 결제 금액>
135,754원
 
<12월 이벤트 배지>
산타
```

<br> 
<br> 