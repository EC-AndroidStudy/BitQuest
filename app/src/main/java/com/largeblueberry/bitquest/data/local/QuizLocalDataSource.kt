package com.largeblueberry.bitquest.data.local

import com.largeblueberry.bitquest.feature_quiz.domain.MultipleChoiceQuiz
import com.largeblueberry.bitquest.feature_quiz.domain.OxQuiz
import com.largeblueberry.bitquest.feature_quiz.domain.Quiz
import com.largeblueberry.bitquest.feature_quiz.domain.QuizModel

interface QuizLocalDataSource {
    fun getQuizzes(): List<QuizModel>
}

class QuizLocalDataSourceImpl : QuizLocalDataSource {
    override fun getQuizzes(): List<QuizModel> {
        return listOf(
            OxQuiz(
                id = 1,
                subject = "소프트웨어공학",
                question = "프로젝트 리스크(Project risks)는 개발될 소프트웨어의 품질이나 성능에 영향을 미치며, 비즈니스 리스크(Business risks)는 조직이나 조달에 영향을 미친다.",
                answer = false,
                explanation = "정답은 X입니다. 소스에 따르면, 프로젝트 리스크(Project risks)는 **일정(schedule) 또는 자원(resources)**에 영향을 미치며, 개발 중인 소프트웨어의 **품질(quality)이나 성능(performance)**에 영향을 미치는 것은 **제품 리스크(Product risks)**입니다. 비즈니스 리스크(Business risks)는 소프트웨어를 개발하거나 조달하는 조직에 영향을 미칩니다."
            ),
            MultipleChoiceQuiz(
                id = 2,
                subject = "컴퓨터시스템구조",
                question = "다음 중 하드웨어 설계의 제1 원칙에 가장 가까운 것은 무엇입니까?",
                choices = listOf(
                    "복잡성은 혁신을 촉진한다",
                    "단순함은 규칙성을 선호한다",
                    "성능은 비용보다 중요하다",
                    "다양성은 호환성을 높인다"
                ),
                answerIndex = 1,
                explanation = "정답은 2번입니다. 하드웨어 설계의 제1 원칙은 '단순함은 규칙성을 선호한다'입니다."
            ),
            OxQuiz(
                id = 3,
                subject = "소프트웨어공학",
                question = "애자일 개발 방법론인 Extreme Programming(XP)에서 리팩토링(Refactoring)은 소프트웨어의 원래 기능은 수정하지 않으면서 코드의 이해도와 유지보수성을 개선하는 활동이다.",
                answer = true,
                explanation = "정답은 O입니다. 리팩토링(Refactoring)은 소프트웨어의 원래 기능(original functionalities)을 수정하지 않으면서소프트웨어를 개선하는 활동입니다. 이는 코드를 단순하고 유지보수 가능하게 유지하여, 코드의 이해도를 높이고, 추후 변경을 더 쉽게 만듭니다."
            ),
            OxQuiz(
                id = 4,
                subject = "소프트웨어공학",
                question = "LLM(대규모 언어 모델)은 생성된 콘텐츠가 아무리 잘 작성되었더라도 확률을 기반으로 내용을 생성하며 합리적인 근거(rationale)가 없기 때문에, 항상 부정확할 가능성을 고려하여 검사 없이 사용해서는 안 된다.",
                answer = true,
                explanation = "정답은 O입니다. LLM은 단순히 확률에 기반하여 콘텐츠를 생성하며, 그 뒤에 합리적인 근거(rationale)는 없습니다. 따라서 생성된 콘텐츠가 잘못되었을 가능성을 항상 고려해야 하며, 적절한 검사 없이 사용해서는 안 됩니다."
            ),
            MultipleChoiceQuiz(
                id = 5,
                subject = "컴퓨터시스템구조",
                question = "MIPS 아키텍처에서 32비트 그룹을 무엇이라고 부릅니까?",
                choices = listOf("바이트(Byte)", "워드(Word)", "블록(Block)", "세그먼트(Segment)"),
                answerIndex = 1,
                explanation = "정답은 2번입니다. MIPS 아키텍처에서 32비트 그룹은 워드(word)라고 불립니다."
            )
        )
    }
}
