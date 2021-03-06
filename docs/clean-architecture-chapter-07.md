# chapter 07 아키텍처 요소 테스트하기

## 테스트 피라미드
- 테스트 피라미드는 테스트가 비싸질수록 테스트의 커버리지 목표는 낮게 잡아야 한다는 것을 보여준다. 그렇지 않으면 새운 기능을 만드는 것보다 테스트를 만드는 데 시간을 더 쓰게 되기 때문이다.

## 단위 테스트로 도메인 엔티티 테스트하기
- 단위 테스트가 도메인 엔티티에 녹아 있는 비즈니스 규칙을 검증하기에 가장 적절한 방법이다. 도메인 엔티티의 행동은 다른 클래스에 거의 의존하지 않기 때문에 다른 종류의 테스트는 필요하지 않다.

## 단위 테스트로 유스케이스 테스트하기
- 테스트에서 어떤 상호작용을 검증하고 싶은지 신중하게 생각해야 한다. 앞의 예제처럼 모든 동작을 검증하는 대신 중요한 핵심만 골라 집중해서 테스트하는 것이 좋다.

## 통합 테스트로 웹 어댑터 테스트하기

## 통합 테스트로 영속성 어댑터 테스트 하기
- 영속성 어댑터의 테스트에는 단위 테스트보다는 통합테스트를 적용하는 것이 합리적이다. 단순히 어댑터의 로직만 검증하고 싶은 게 아니라 데이터베이스 매핑도 검증하고 싶기 때문이다.

## 시스템 테스트로 주요 경로 테스트하기
- 시스템 테스트는 단위 테스트나 통합 테스트가 할 수 있는 것보다 훨씬 더 실제 사용자를 잘 흉내 내기 때문에 사용자 관점에서 애플리케이션을 검증할 수 있다.
- 일반적으로 시스템 테스트는 단위 테스트와 통합 테스트가 발견하는 버그와는 또 다른 종류의 버그를 발견해서 수정할 수 있게 해준다.

## 얼마만큼의 테스트가 충분할까?
- 라인커버리지는 테스트 성공을 측정하는 데 있어서는 잘못된 지표다.
- 리팩터링할 때마다 테스트 코드도 변경해야 한다면 테스트는 테스트로서의 가치를 잃는다.

## 유지보수 가능한 소프트웨어를 만드는 데 어떻게 도움이 될까?
- 만약 포트가 아주 작고 핵심만 담고 있다면 모킹하는 것이 아주 쉬울 것이다.
- 모킹하는 것이 너무 버거워지거나 코드의 특정 부분을 커버하기 위해 어떤 종류의 테스트를 써야 할지 모르겠다면 이는 경고 신호다.
