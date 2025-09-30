# 🚀 BitQuest - CS 개념 마스터 퀴즈 앱

> **해커톤 MVP 프로젝트** | 컴퓨터공학 전공자 및 주니어 개발자를 위한 경험 기반 CS 학습 플랫폼

[![Kotlin](https://img.shields.io/badge/Kotlin-2.0.21-purple.svg)](https://kotlinlang.org)
[![Compose](https://img.shields.io/badge/Jetpack%20Compose-2024.12.01-blue.svg)](https://developer.android.com/jetpack/compose)
[![Gemini AI](https://img.shields.io/badge/Gemini%20AI-Latest-orange.svg)](https://ai.google.dev)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

## 📋 프로젝트 개요

**BitQuest**는 컴퓨터공학 전공자 및 주니어 개발자의 CS 개념 이해도 향상을 위한 경험 기반 퀴즈 앱입니다.
실무에서 자주 마주치는 CS 개념들을 퀴즈 형태로 제공하고, Gemini AI가 생성한 실전 중심의 해설을 통해 효과적인 학습을 돕습니다.

### 🎯 핵심 기능
- **💻 CS 전문 퀴즈**: 안드로이드, git 등
- **🧠 실무 중심 문제**: 실제 개발 현장에서 마주치는 상황 기반 문제
- **🤖 AI 기반 해설**: Gemini AI가 생성하는 실전 예제와 함께하는 상세 해설
- **📊 학습 진도 관리**: 분야별 이해도 추적 및 약점 분석
- **💾 스마트 캐싱**: Room DB를 활용한 오프라인 지원 및 빠른 로딩
- **🚀 최적화된 UX**: Jetpack Compose 기반의 현대적이고 직관적인 UI

### 🎓 타겟 사용자
- **컴퓨터공학 전공 학생**: CS 기초 개념 정리 및 시험 대비
- **취업 준비생**: 기술 면접 대비 CS 개념 학습

## 🏗️ 기술 스택

### **Frontend**
- **Jetpack Compose 2024.12.01** - 현대적인 UI 프레임워크
- **Material 3** - 최신 디자인 시스템
- **Compose Navigation** - 선언적 네비게이션

### **Architecture**
- **MVVM + Clean Architecture** - 확장 가능하고 테스트 가능한 구조
- **Hilt** - 의존성 주입
- **Coroutines & Flow** - 비동기 처리

### **Data**
- **Room Database** - 로컬 데이터 저장 및 캐싱
- **Gemini AI API** - AI 기반 해설 생성
- **SharedPreferences** - 사용자 설정 저장

### **Testing & Tools**
- **JUnit 5** - 단위 테스트
- **Compose Test** - UI 테스트
- **Detekt** - 코드 품질 관리

## 🚀 시작하기

### 📋 사전 요구사항
- Android Studio Ladybug (2024.2.1) 이상
- **JDK 21** 이상
- **Android SDK 35** (Target SDK)
- **Minimum SDK 35**
- Gemini AI API 키

### 🔧 설치 및 실행

1. **저장소 클론**
 ```bash
 git clone https://github.com/yourteam/bitquest-android.git
 cd bitquest-android
 ```

2. **API 키 설정**
 ```bash
 # local.properties 파일에 추가
 GEMINI_API_KEY=your_gemini_api_key_here
 ```

3. **프로젝트 빌드**
 ```bash
 ./gradlew build
 ```

4. **앱 실행**
 ```bash
 ./gradlew installDebug
 ```

## 📁 프로젝트 구조

```
app/src/main/java/com/yourteam/bitquest/
├── 🎨 ui/                     # Compose UI 레이어
│   ├── theme/                 # Material 3 테마
│   ├── components/            # 재사용 가능한 Composable
│   ├── screens/               # 화면별 Composable + ViewModel
│   └── navigation/            # 네비게이션 설정
├── 🏗️ domain/                 # 비즈니스 로직 레이어
│   ├── model/                 # 도메인 모델
│   ├── repository/            # 저장소 인터페이스
│   └── usecase/               # 유스케이스
├── 💾 data/                   # 데이터 레이어
│   ├── local/                 # Room DB, SharedPreferences
│   ├── remote/                # Gemini API
│   └── repository/            # 저장소 구현체
├── 🔧 di/                     # Hilt 의존성 주입
└── 🛠️ util/                   # 유틸리티 및 확장 함수
```

## 🎯 주요 화면

### 1. **스플래시 화면**
- 앱 초기화 및 CS 퀴즈 데이터 로딩
- 백그라운드에서 최신 문제 동기화

### 2. **메인 화면**
- CS 분야별 카테고리 그리드
- **안드로이드** : 생명주기, compose ui
- **git** : 브랜치 전략, 기본 명령어, 충돌 해결
- 각 분야별 학습 진도율 및 정답률 표시

### 3. **퀴즈 화면**
- 실무 상황 기반 객관식, OX 문제
- 직관적인 답안 선택 UI

### 4. **결과 화면**
- 분야별 점수 및 정답률 분석
- AI 생성 상세 해설
- 틀린 문제 복습 및 관련 개념 추천

### 5. **통계 화면**
- 전체 학습 진도 대시보드
- 분야별 강점/약점 분석

## 👥 팀 구성 및 역할

### 🏆 **팀 리더 (Senior Developer)**
- 전체 아키텍처 설계 및 코드 리뷰
- Gemini AI 연동 및 복잡한 비즈니스 로직
- CS 문제 데이터 큐레이션 및 검증
- 팀원 멘토링 및 기술적 의사결정

### 👨‍💻 **Frontend Developer**
- Jetpack Compose UI 구현
- Material 3 테마 및 컴포넌트 개발
- 사용자 경험 최적화

### 👩‍💻 **Backend Developer**
- Room Database 설계 및 구현
- Repository 패턴 구현
- CS 문제 데이터 초기화 및 캐싱 로직
- 성능 최적화

### 🧪 **QA Engineer**
- CS 문제 정확성 검증
- UI/UX 테스트 및 버그 리포트
- 성능 테스트 및 최적화
- 사용자 시나리오 테스트

## 📊 개발 진행 상황

### ✅ **완료된 작업**
- [x] 프로젝트 초기 설정 (JDK 21, Target SDK 35)
- [x] Clean Architecture 기반 폴더 구조 설계
- [x] Room Database 스키마 정의 (CS 문제 특화)
- [x] Hilt 의존성 주입 모듈 설정
- [x] CS 분야별 초기 문제 데이터 수집

### 🔄 **진행 중인 작업**
- [ ] Jetpack Compose UI 컴포넌트 개발
- [ ] 코드 스니펫 렌더링 컴포넌트
- [ ] ViewModel 및 UseCase 구현
- [ ] Gemini AI API 연동 (CS 특화 프롬프트)
- [ ] 네비게이션 플로우 구현

### 📅 **예정된 작업**
- [ ] AI 해설 캐싱 및 오프라인 지원
- [ ] 학습 진도 추적 시스템
- [ ] 통계 대시보드 구현
- [ ] 단위 테스트 및 UI 테스트 작성
- [ ] 성능 최적화 및 메모리 관리

### 🌿 **Git 브랜치 전략**
```
main                          # 배포 브랜치
├── develop                  # 개발 통합 브랜치
├── feature/ui-cs-theme     # CS 특화 UI 테마
├── feature/quiz-engine     # 퀴즈 엔진 개발
├── feature/ai-integration  # AI 해설 연동
└── feature/progress-tracking # 학습 진도 관리
```

### 📋 **Pull Request 규칙**
1. **브랜치명**: `feature/기능명` 또는 `fix/버그명`
2. **커밋 메시지**: `[feat|fix|docs|style|refactor|test]: 설명`
3. **코드 리뷰**: 최소 1명의 승인 필요
4. **테스트**: 관련 테스트 케이스 포함

## 📈 성능 지표

### 🚀 **목표 성능**
- **앱 시작 시간**: < 2초
- **퀴즈 로딩 시간**: < 1초
- **AI 해설 생성**: < 5초
- **코드 렌더링**: < 500ms
- **메모리 사용량**: < 200MB

### 📊 **현재 성능** (측정 예정)
- **앱 시작 시간**: TBD
- **퀴즈 로딩 시간**: TBD
- **AI 해설 생성**: TBD
- **메모리 사용량**: TBD

## 🔐 보안 고려사항

- **API 키 보안**: local.properties 및 환경변수 사용
- **데이터 암호화**: Room DB 암호화 적용 (CS 문제 보호)
- **네트워크 보안**: HTTPS 통신 및 Certificate Pinning
- **사용자 데이터**: 개인정보 최소 수집 원칙

## 🎓 CS 문제 카테고리

### 📚 **안드로이드 (Android)**
- 액티비티 생명주기
- MVVM 아키텍쳐
- 코루틴

### ⚡ **git**
- 기본 명령어 (clone, commit, push, pull)
- 브랜치 전략

## 📄 라이선스

이 프로젝트는 [MIT 라이선스](LICENSE) 하에 배포됩니다.

## 📞 연락처

- **팀 리더**: [onlym6659@gmail.com](mailto:onlym6659@gmail.com)
- **프로젝트 저장소**: [GitHub Repository](https://github.com/EC-AndroidStudy/BitQuest.git)
- **이슈 트래커**: [GitHub Issues](https://github.com/EC-AndroidStudy/BitQuest.git/issues)

---