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
                question = "프로젝트 리스크(Project risks)는 개발될 소프트웨어의 품질이나 성능에 영향을 미치며, 비즈니스 리스크(Business risks)는 조직이나 조달에 영향을 미친다.",
                answer = false,
                explanation = "정답은 X입니다. 소스에 따르면, 프로젝트 리스크(Project risks)는 **일정(schedule) 또는 자원(resources)**에 영향을 미치며, 개발 중인 소프트웨어의 **품질(quality)이나 성능(performance)**에 영향을 미치는 것은 **제품 리스크(Product risks)**입니다. 비즈니스 리스크(Business risks)는 소프트웨어를 개발하거나 조달하는 조직에 영향을 미칩니다."
            ),
            Quiz(
                id = 2,
                question = "폭포수 모델(Waterfall model)은 프로젝트를 분리된 단계로 유연하게 분할하기 때문에, 요구사항이 불안정하고 변화에 민감한 시스템에 주로 적합하다.",
                answer = false,
                explanation = "정답은 X입니다. 폭포수 모델(waterfall model)은 프로젝트를 구별되는 단계로 비유연하게(Inflexible) 분할하여, 변화하는 고객 요구사항에 대응하기 어렵습니다. 따라서 이 모델은 요구사항이 잘 이해되고 설계 과정 중 변경이 상당히 제한적일 때만적절합니다."
            ),
            Quiz(
                id = 3,
                question = "애자일 개발 방법론인 Extreme Programming(XP)에서 리팩토링(Refactoring)은 소프트웨어의 원래 기능은 수정하지 않으면서 코드의 이해도와 유지보수성을 개선하는 활동이다.",
                answer = true,
                explanation = "정답은 O입니다. 리팩토링(Refactoring)은 소프트웨어의 원래 기능(original functionalities)을 수정하지 않으면서소프트웨어를 개선하는 활동입니다. 이는 코드를 단순하고 유지보수 가능하게 유지하여, 코드의 이해도를 높이고, 추후 변경을 더 쉽게 만듭니다."
            ),
            Quiz(
                id = 4,
                question = "LLM(대규모 언어 모델)은 생성된 콘텐츠가 아무리 잘 작성되었더라도 확률을 기반으로 내용을 생성하며 합리적인 근거(rationale)가 없기 때문에, 항상 부정확할 가능성을 고려하여 검사 없이 사용해서는 안 된다.",
                answer = true,
                explanation = "정답은 O입니다. LLM은 단순히 확률에 기반하여 콘텐츠를 생성하며, 그 뒤에 합리적인 근거(rationale)는 없습니다. 따라서 생성된 콘텐츠가 잘못되었을 가능성을 항상 고려해야 하며, 적절한 검사 없이 사용해서는 안 됩니다."
            ),
            Quiz(
                id = 5,
                question = "베이스라인(Baseline)은 시스템을 구성하는 구성요소 버전들의 집합이며, 베이스라인이 설정되면 해당 베이스라인을 이루는 구성요소의 버전은 변경될 수 없다.",
                answer = true,
                explanation = "정답은 O입니다. 베이스라인(Baseline)은 시스템을 구성하는 구성요소 버전들의 컬렉션입니다. 베이스라인은 통제되며, 이는 시스템을 구성하는 구성요소의 버전이 변경될 수 없다는 것을 의미합니다. 이를 통해 구성요소를 항상 재생성할 수 있습니다."
            ),
            Quiz(
                id = 6,
                question = "비기능 요구사항(Non-functional requirements) 중 '목표(Goal)'는 사용 편의성과 같이 사용자의 일반적인 의도를 나타내지만, '검증 가능한 비기능 요구사항(Verifiable non-functional requirement)'은 객관적으로 테스트 가능한 측정치를 사용한다.",
                answer = true,
                explanation = "정답은 O입니다. 비기능 요구사항을 명시하는 데 있어 '목표(Goal)'는 사용 용이성과 같이 사용자의 일반적인 의도를 의미하지만, '검증 가능한 비기능 요구사항'은 객관적으로 테스트할 수 있는 측정치를 사용하는 진술입니다. 예를 들어, 신뢰성(Reliability)의 측정치로는 평균 고장 시간(Mean time to failure) 등이 있습니다."
            ),
        )
    }
}