package com.largeblueberry.bitquest.data.local

import com.largeblueberry.bitquest.domain.model.Quiz

interface QuizLocalDataSource {
    fun getQuizzes(): List<Quiz>
}

class QuizLocalDataSourceImpl : QuizLocalDataSource {
    override fun getQuizzes(): List<Quiz> {
        return listOf(
            Quiz(
                id = 1,
                subject = "소프트웨어공학",
                question = "프로젝트 리스크(Project risks)는 개발될 소프트웨어의 품질이나 성능에 영향을 미치며, 비즈니스 리스크(Business risks)는 조직이나 조달에 영향을 미친다.",
                answer = false,
                explanation = "정답은 X입니다. 소스에 따르면, 프로젝트 리스크(Project risks)는 **일정(schedule) 또는 자원(resources)**에 영향을 미치며, 개발 중인 소프트웨어의 **품질(quality)이나 성능(performance)**에 영향을 미치는 것은 **제품 리스크(Product risks)**입니다. 비즈니스 리스크(Business risks)는 소프트웨어를 개발하거나 조달하는 조직에 영향을 미칩니다."
            ),
            Quiz(
                id = 2,
                subject = "소프트웨어공학",
                question = "폭포수 모델(Waterfall model)은 프로젝트를 분리된 단계로 유연하게 분할하기 때문에, 요구사항이 불안정하고 변화에 민감한 시스템에 주로 적합하다.",
                answer = false,
                explanation = "정답은 X입니다. 폭포수 모델(waterfall model)은 프로젝트를 구별되는 단계로 비유연하게(Inflexible) 분할하여, 변화하는 고객 요구사항에 대응하기 어렵습니다. 따라서 이 모델은 요구사항이 잘 이해되고 설계 과정 중 변경이 상당히 제한적일 때만적절합니다."
            ),
            Quiz(
                id = 3,
                subject = "소프트웨어공학",
                question = "애자일 개발 방법론인 Extreme Programming(XP)에서 리팩토링(Refactoring)은 소프트웨어의 원래 기능은 수정하지 않으면서 코드의 이해도와 유지보수성을 개선하는 활동이다.",
                answer = true,
                explanation = "정답은 O입니다. 리팩토링(Refactoring)은 소프트웨어의 원래 기능(original functionalities)을 수정하지 않으면서소프트웨어를 개선하는 활동입니다. 이는 코드를 단순하고 유지보수 가능하게 유지하여, 코드의 이해도를 높이고, 추후 변경을 더 쉽게 만듭니다."
            ),
            Quiz(
                id = 4,
                subject = "소프트웨어공학",
                question = "LLM(대규모 언어 모델)은 생성된 콘텐츠가 아무리 잘 작성되었더라도 확률을 기반으로 내용을 생성하며 합리적인 근거(rationale)가 없기 때문에, 항상 부정확할 가능성을 고려하여 검사 없이 사용해서는 안 된다.",
                answer = true,
                explanation = "정답은 O입니다. LLM은 단순히 확률에 기반하여 콘텐츠를 생성하며, 그 뒤에 합리적인 근거(rationale)는 없습니다. 따라서 생성된 콘텐츠가 잘못되었을 가능성을 항상 고려해야 하며, 적절한 검사 없이 사용해서는 안 됩니다."
            ),
            Quiz(
                id = 5,
                subject = "소프트웨어공학",
                question = "베이스라인(Baseline)은 시스템을 구성하는 구성요소 버전들의 집합이며, 베이스라인이 설정되면 해당 베이스라인을 이루는 구성요소의 버전은 변경될 수 없다.",
                answer = true,
                explanation = "정답은 O입니다. 베이스라인(Baseline)은 시스템을 구성하는 구성요소 버전들의 컬렉션입니다. 베이스라인은 통제되며, 이는 시스템을 구성하는 구성요소의 버전이 변경될 수 없다는 것을 의미합니다. 이를 통해 구성요소를 항상 재생성할 수 있습니다."
            ),
            Quiz(
                id = 6,
                subject = "소프트웨어공학",
                question = "비기능 요구사항(Non-functional requirements) 중 '목표(Goal)'는 사용 편의성과 같이 사용자의 일반적인 의도를 나타내지만, '검증 가능한 비기능 요구사항(Verifiable non-functional requirement)'은 객관적으로 테스트 가능한 측정치를 사용한다.",
                answer = true,
                explanation = "정답은 O입니다. 비기능 요구사항을 명시하는 데 있어 '목표(Goal)'는 사용 용이성과 같이 사용자의 일반적인 의도를 의미하지만, '검증 가능한 비기능 요구사항'은 객관적으로 테스트할 수 있는 측정치를 사용하는 진술입니다. 예를 들어, 신뢰성(Reliability)의 측정치로는 평균 고장 시간(Mean time to failure) 등이 있습니다."
            ),
            Quiz(
                id = 7,
                subject = "컴퓨터시스템구조",
                question = "하드웨어 설계의 원칙 1은 \"단순함은 규칙성을 선호한다\"이다.",
                answer = true,
                explanation = "하드웨어 설계의 제1 원칙은 단순함이 규칙성(regularity)을 선호한다는 것입니다."
            ),
            Quiz(
                id = 8,
                subject = "컴퓨터시스템구조",
                question = "Amdahl의 법칙(Amdahl's Law)에 따르면, 주어진 개선으로 얻을 수 있는 성능 향상은 개선된 기능이 사용되는 양에 의해 제한된다.",
                answer = true,
                explanation = "Amdahl의 법칙은 주어진 개선이 사용되는 정도에 따라 성능 향상이 제한된다는 것을 명시합니다. 이는 \"수확 체감의 법칙\"의 정량적 버전입니다."
            ),
            Quiz(
                id = 9,
                subject = "컴퓨터시스템구조",
                question = "컴퓨터 아키텍처에서 파이프라이닝(pipelining)은 연산을 병렬로 수행하여 성능을 얻는 특정 패턴을 지칭한다.",
                answer = true,
                explanation = "파이프라이닝은 컴퓨터 아키텍처에서 매우 널리 퍼져있는 병렬처리 패턴입니다."
            ),
            Quiz(
                id = 10,
                subject = "컴퓨터시스템구조",
                question = "메모리 계층 구조(Hierarchy of Memories)에서 가장 빠르고 작으며 비트당 비용이 가장 높은 메모리는 계층 구조의 맨 위에 위치한다.",
                answer = true,
                explanation = "메모리 계층 구조는 가장 빠르고 비싸며 작은 메모리가 상단에, 가장 느리고 저렴하며 큰 메모리가 하단에 위치하여 상충되는 요구를 해결합니다."
            ),
            Quiz(
                id = 11,
                subject = "컴퓨터시스템구조",
                question = "MIPS 아키텍처에서 레지스터의 크기는 32비트이며, 32비트 그룹을 워드(word)라고 부른다.",
                answer = true,
                explanation = "MIPS 아키텍처에서 레지스터 크기는 32비트이며, 32비트 그룹은 워드라고 불립니다."
            ),
            Quiz(
                id = 12,
                subject = "컴퓨터시스템구조",
                question = "투스 보수(two’s complement) 표현을 사용하는 경우, 숫자가 양수인지 음수인지 확인하려면 최상위 비트(most significant bit)만 확인하면 된다.",
                answer = true,
                explanation = "투스 보수 표현의 장점은 모든 음수가 최상위 비트에 1을 가지므로, 하드웨어는 이 비트만으로 양수인지 음수인지 테스트할 수 있다는 점입니다."
            ),
            Quiz(
                id = 13,
                subject = "컴퓨터시스템구조",
                question = "모든 MIPS 명령어는 설계 원칙(단순함은 규칙성을 선호)을 따라 데이터 워드와 동일한 크기인 32비트 길이를 가진다.",
                answer = true,
                explanation = "단순함은 규칙성을 선호한다는 설계 원칙에 따라, 모든 MIPS 명령어는 데이터 워드와 같은 크기인 32비트 길이입니다."
            ),
            Quiz(
                id = 14,
                subject = "컴퓨터시스템구조",
                question = "컴파일러는 C 변수를 레지스터에 연결하지만, 배열이나 구조체 같은 데이터 구조를 메모리 위치에 할당하는 것은 운영체제가 담당한다.",
                answer = false,
                explanation = "컴파일러는 변수를 레지스터에 연결할 뿐만 아니라 배열 및 구조체와 같은 데이터 구조를 메모리 위치에 할당합니다."
            ),
            Quiz(
                id = 15,
                subject = "컴퓨터시스템구조",
                question = "직접 매핑 캐시(direct-mapped cache)에서 각 메모리 위치는 캐시 내의 정확히 하나의 위치로 매핑된다.",
                answer = true,
                explanation = "직접 매핑 캐시는 메모리 내의 각 단어가 캐시 내의 정확히 한 위치에만 배치될 수 있도록 지정하는 캐시 구조입니다."
            ),
            Quiz(
                id = 16,
                subject = "컴퓨터시스템구조",
                question = "에지 트리거 클럭킹(edge-triggered clocking) 방식에서는 상태 요소(예: 레지스터)가 클럭 에지에서만 업데이트되므로, 한 클럭 사이클 내에서 레지스터 파일에 대한 읽기 및 쓰기를 동시에 수행할 수 있다.",
                answer = true,
                explanation = "에지 트리거 클럭킹 방식은 단일 클럭 사이클 내에서 상태 요소의 내용을 읽고 쓸 수 있도록 허용합니다. 읽기는 이전 클럭 사이클에 작성된 값을 얻고, 쓰기는 다음 클럭 사이클의 읽기에서 사용 가능합니다."
            ),
            Quiz(
                id = 17,
                subject = "컴퓨터시스템구조",
                question = "명령어 세트 아키텍처(Instruction Set Architecture, ISA)와 하드웨어 사이의 주요 인터페이스는 명령어 집합 아키텍처(instruction set architecture)이다.",
                answer = true,
                explanation = "명령어 집합 아키텍처는 하드웨어와 저수준 소프트웨어 간의 핵심 인터페이스입니다."
            ),
            Quiz(
                id = 18,
                subject = "컴퓨터시스템구조",
                question = "동적 에너지(dynamic energy)는 트랜지스터가 0에서 1 또는 그 반대로 상태를 전환할 때 소비되는 주요 에너지원으로, 이는 정전용량 부하(capacitive load)와 전압의 제곱에 비례한다 (Energy∝Capacitive load×Voltage^2).",
                answer = true,
                explanation = "CMOS에서 동적 에너지는 트랜지스터가 상태를 전환할 때 소비되며, 정전용량 부하와 전압의 제곱에 비례하는 관계를 가집니다."
            )
        )
    }
}