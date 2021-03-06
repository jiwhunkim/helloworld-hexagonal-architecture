# chapter 11 의식적으로 지름길 사용하기

지름길을 방지하기 위해서는 먼저 지름길 자체를 파악해야 한다.

## 왜 지름길은 깨진 창문 같을까?
- 품질이 떨어진 코드에서 작업할 때 더 낮은 품질의 코드를 추가하기가 쉽다.
- 코딩 규칙을 많이 어긴 코드에서 작업할 때 또 다른 규칙을 어기기도 쉽다.
- 지름길을 만히 사용하 코드에서 작업할 때 또 다른 지름길을 추가하기도 쉽다.

## 깨끗한 상태로 시작할 책임
- 가능한 한 지름길을 거의 쓰지 않고 기술 부채를 지지 않은 채로 프로젝트를 시작하는 것이 중요하다. 지름길이 몰래 스며드는 순간 깨진 창문과 같아져 버려서 더 많은 지름길을 끌어들이기 때문이다.

## 유스케이스 간 모델 공유하기
- 유스케이스 간 입출력 모델을 공유하는 것은 유스케이스들이 기능적으로 묶여 있을 때 유효하다. 즉 특정 요구사항을 공유할 때 괜찮다는 의미다. 이 경우 특정 세부사항을 변경 할 경우 실제로 두 유스케이스 모두에 영향을 주고 싶은 것이다.

## 도메인 엔티티를 입출력 모델로 사용하기
- 유스케이스의 변경이 도메인 엔티티까지 전파되길 바라진 않을 것이기 때문이다.
- 많은 유스케이스가 간단한 생성 또는 업데이트 유스케이스로 시작해서 시간이 지나면서 복잡한 도메인 로직 괴물이 되어간다는 사실 때문이다.

## 인커밍 포트 건너뛰기
- 전용 인커밍 포트를 유지하면 한눈에 진입점을 식별할 수 있다. 이는 새로운 개발자가 코드를 파악할 때 특히 더 도움이 된다.
- 인커밍 포트를 유지해야하는 또 다른 이유는 아키텍처를 쉽게 강제할 수 있기 때문이다.

## 애플리케이션 서비스 건너뛰기
- 단순히 전달만 하는 보일러플레이트 코드가 가득한 서비스가 많아지는 것을 방지하기 위해 간단한 CRUD 케이스에서는 애플리케이션 서비스를 건너뛰기로 결정할 수도 있다. 하지만 유스케이스가 엔티티를 단순히 생성, 업데이트, 삭제하는 것보다 더 많은 일을 하게 되면 애플리케이션 서비스를 만든다는 명확한 가이드라인을 팀에 정해둬야 한다.

## 유지보수 가능한 소프트웨어를 만드는 데 어떻게 도움이 될까?
- 어떤 경우든 아키텍처에 대해, 그리고 왜 특정 지름길을 선택했는가에 대한 기록을 남겨서 나중에 우리 자신 또는 프로젝트를 인계받는 이들이 이 결정에 대해 다시 평가할 수 있게 하자.
