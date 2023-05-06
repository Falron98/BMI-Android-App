package com.example.bmi_calc

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.MessageFormat
import java.util.*

class Quiz : AppCompatActivity() {
    var txtView_question_number: TextView? = null
    var txtView_question: TextView? = null
    var ans1: Button? = null
    var ans2: Button? = null
    var ans3: Button? = null
    var ans4: Button? = null
    lateinit var res: Button
    var question: String? = null
    var good_answer: String? = null
    var numberList: List<Int?>? = null
    lateinit var answers: Array<String>
    lateinit var questions_array: Array<String>
    lateinit var answers_array: Array<String>
    lateinit var good_answer_array: Array<String>
    var questions_map: MutableMap<String?, Array<String?>>? = null
    var score = 0
    var totalQuestionsNumber = 0
    var currentQuestionsNumber = 0
    var random_number = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        questions_array = resources.getStringArray(R.array.questions)
        answers_array = resources.getStringArray(R.array.answers)
        good_answer_array = resources.getStringArray(R.array.good_answer)
        totalQuestionsNumber = questions_array.size
        txtView_question_number = findViewById(R.id.txtView_question_number)
        txtView_question = findViewById(R.id.txtView_question)
        ans1 = findViewById(R.id.button_answer1)
        ans2 = findViewById(R.id.button_answer2)
        ans3 = findViewById(R.id.button_answer3)
        ans4 = findViewById(R.id.button_answer4)
        res = findViewById(R.id.button_reset)
        currentQuestionsNumber = 0
        questions_map = HashMap()
        for (i in 0 until totalQuestionsNumber) {
            question = questions_array[i]
            answers = answers_array[i].split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            good_answer = good_answer_array[i]
            (questions_map as HashMap<String?, Array<String?>>)[question] = arrayOf(TextUtils.join("\n", answers), good_answer)
        }
        for (button in Arrays.asList(ans1, ans2, ans3, ans4)) {
            button!!.setOnClickListener { v: View? ->
                if ((questions_map as HashMap<String?, Array<String?>>).get(questions_array[random_number])!![1] == button.text.toString()) {
                    score += 1
                }
                (questions_map as HashMap<String?, Array<String?>>).remove(questions_array[random_number])
                next_question()
            }
        }
        res.setOnClickListener(View.OnClickListener { v: View? -> reset_quiz() })
        next_question()
    }

    private fun reset_quiz() {
        currentQuestionsNumber = 0
        score = 0
        questions_map = HashMap()
        for (i in 0 until totalQuestionsNumber) {
            question = questions_array[i]
            answers = answers_array[i].split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            good_answer = good_answer_array[i]
            (questions_map as HashMap<String?, Array<String?>>)[question] = arrayOf(TextUtils.join("\n", answers), good_answer)
        }
        next_question()
    }

    private fun next_question() {
        if (currentQuestionsNumber >= totalQuestionsNumber) {
            finishQuiz()
        } else {
            for (j in 0..3) {
                val numbers = arrayOf(0, 1, 2, 3) // Replace with your desired numbers
                numberList = Arrays.asList(*numbers)
                Collections.shuffle(numberList, Random())
            }
            val rand = Random()
            do {
                random_number = rand.nextInt(totalQuestionsNumber)
            } while (!questions_map!!.containsKey(questions_array[random_number]))
            currentQuestionsNumber += 1
            txtView_question_number!!.text = MessageFormat.format("{0}/{1}", currentQuestionsNumber, totalQuestionsNumber)
            txtView_question!!.text = questions_array[random_number]
            answers = questions_map!![questions_array[random_number]]!![0]!!.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            ans1!!.text = answers[numberList!![0]!!]
            ans2!!.text = answers[numberList!![1]!!]
            ans3!!.text = answers[numberList!![2]!!]
            ans4!!.text = answers[numberList!![3]!!]
        }
    }

    fun finishQuiz() {
        AlertDialog.Builder(this)
                .setTitle(getString(R.string.quiz_finish_title))
                .setMessage(MessageFormat.format(getString(R.string.quiz_finish_score), score, totalQuestionsNumber))
                .setPositiveButton(getString(R.string.quiz_finish_button)) { dialogInterface: DialogInterface?, i: Int -> reset_quiz() }
                .setCancelable(false)
                .show()
    }
}