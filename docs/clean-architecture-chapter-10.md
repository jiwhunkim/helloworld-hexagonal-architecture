# chapter 10 아키텍처 경계 강제하기

일정 규모 이상의 모든 프로젝트에서는 시간이 지나면서 아키텍처가 서서히 무너지게 된다. 계층 간의 경계가 약화되고, 코드는 점점 더 테스트하기 어려워지고, 새로운 기능을 구현하는 데 점점 더 많은 시간이 든다.

## 경계와 의존성
- 의존성 규칙에 따르면 계층 경계를 넘는 의존성은 항상 안쪽 방향으로 향해야 한다.

## 접근 제한자
- 자바 패키지를 통해 클래스들을 응집적인 '모듈'로 만들어 주기 때문이다.

## 컴파일 후 체크
- ArchUnit API를 이용하면 적은 작업만으로도 육각형 아키텍처 내에서 관련된 모든 패키지를 명시할 수 있는 일종의 도메인 특화 언어를 만들 수 있고, 패키지 사이의 의존성 방향이 올바른지 자동으로 체크할 수 있다.

## 빌드 아티팩트
- 빌드 스크립트를 유지보수하는 비용을 수반하기 때문에 아키텍쳐를 여러 개의 빌드 모듈로 나누기 전에 아키텍처가 어느 정도는 안정된 상태여야 한다.

## 유지보수 가능한 소프트웨어를 만드는 데 어떻게 도움이 될까?
- 만약 의존성이 거대한 진흙 덩어리가 된다면 아키텍처 역시 거대한 진흙 덩어리가 된다.
- 아키텍처 경계를 강제하고 시간이 지나도 유지보수하기 좋은 코드를 만들기 위해 세 가지 접근 방식 모두를 함께 조합해서 사용할 수 있다.
