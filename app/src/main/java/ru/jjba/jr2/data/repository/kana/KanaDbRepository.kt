package ru.jjba.jr2.data.repository.kana

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import ru.jjba.jr2.App
import ru.jjba.jr2.data.db.AppDatabase
import ru.jjba.jr2.data.db.dao.KanaDao
import ru.jjba.jr2.domain.entity.Kana

class KanaDbRepository(
        db: AppDatabase = App.instance.db,
        private val scheduler: Scheduler = Schedulers.io()
) {
    private val kanaDao: KanaDao = db.getKanaDao()

    fun getAll(): Flowable<List<Kana>> =
            kanaDao.getAll()
                    .subscribeOn(scheduler)

    fun insert(piecesOfKana: List<Kana>): Completable =
            Completable.fromCallable { kanaDao.insert(piecesOfKana) }
                    .subscribeOn(scheduler)

    // TODO : move to file "kana.json" and load to local DB where new instance is creating
    val kana = listOf(Kana("1", "あ", "ア", "a", "а", false))
    /*listOf(
            //main character
            Kana(UUID.randomUUID(), "た", "タ", "ta", "та"),
            Kana(UUID.randomUUID(), "ち", "チ", "chi", "ти"),
            Kana(UUID.randomUUID(), "つ", "ツ", "tsu", "ту"),
            Kana(UUID.randomUUID(), "て", "テ", "te", "тэ"),
            Kana(UUID.randomUUID(), "と", "ト", "to", "то"),

            Kana(UUID.randomUUID(), "な", "ナ", "na", "на"),
            Kana(UUID.randomUUID(), "に", "ニ", "ni", "ни"),
            Kana(UUID.randomUUID(), "ぬ", "ヌ", "nu", "ну"),
            Kana(UUID.randomUUID(), "ね", "ネ", "ne", "нэ"),
            Kana(UUID.randomUUID(), "の", "ノ", "no", "но"),

            Kana(UUID.randomUUID(), "は", "ハ", "ha", "ха"),
            Kana(UUID.randomUUID(), "ひ", "ヒ", "hi", "хи"),
            Kana(UUID.randomUUID(), "ふ", "フ", "fu", "фу"),
            Kana(UUID.randomUUID(), "へ", "ヘ", "he", "хэ"),
            Kana(UUID.randomUUID(), "ほ", "ホ", "ho", "хо"),

            Kana(UUID.randomUUID(), "ま", "マ", "ma", "ма"),
            Kana(UUID.randomUUID(), "み", "ミ", "mi", "ми"),
            Kana(UUID.randomUUID(), "む", "ム", "mu", "му"),
            Kana(UUID.randomUUID(), "め", "メ", "me", "мэ"),
            Kana(UUID.randomUUID(), "も", "モ", "mo", "мо"),

            Kana(UUID.randomUUID(), "や", "ヤ", "ya", "я"),
            Kana(UUID.randomUUID(), "ゆ", "ユ", "yu", "ю"),
            Kana(UUID.randomUUID(), "よ", "ヨ", "yo", "ё"),

            Kana(UUID.randomUUID(), "ら", "ラ", "ra", "ра"),
            Kana(UUID.randomUUID(), "り", "リ", "ri", "ри"),
            Kana(UUID.randomUUID(), "る", "ル", "ru", "ру"),
            Kana(UUID.randomUUID(), "れ", "レ", "re", "рэ"),
            Kana(UUID.randomUUID(), "ろ", "ロ", "ro", "ро"),

            Kana(UUID.randomUUID(), "わ", "ワ", "wa", "ва"),
            Kana(UUID.randomUUID(), "を", "ヲ", "wo", "о"),
            Kana(UUID.randomUUID(), "ん", "ン", "n", "н"),

            //nigori
            Kana(UUID.randomUUID(), "が", "ガ", "ga", "га"),
            Kana(UUID.randomUUID(), "ぎ", "ギ", "gi", "ги"),
            Kana(UUID.randomUUID(), "ぐ", "グ", "gu", "гу"),
            Kana(UUID.randomUUID(), "げ", "ゲ", "ge", "ге"),
            Kana(UUID.randomUUID(), "ご", "ゴ", "go", "го"),

            Kana(UUID.randomUUID(), "ざ", "ザ", "za", "дза"),
            Kana(UUID.randomUUID(), "じ", "ジ", "ji", "дзи"),
            Kana(UUID.randomUUID(), "ず", "ズ", "zu", "дзу"),
            Kana(UUID.randomUUID(), "ぜ", "ゼ", "ze", "дзэ"),
            Kana(UUID.randomUUID(), "ぞ", "ゾ", "zo", "дзо"),

            Kana(UUID.randomUUID(), "だ", "ダ", "da", "да"),
            Kana(UUID.randomUUID(), "ぢ", "ヂ", "ji", "дзи"),
            Kana(UUID.randomUUID(), "づ", "ヅ", "zu", "дзу"),
            Kana(UUID.randomUUID(), "で", "デ", "de", "дэ"),
            Kana(UUID.randomUUID(), "ど", "ド", "do", "до"),

            Kana(UUID.randomUUID(), "ば", "バ", "ba", "ба"),
            Kana(UUID.randomUUID(), "び", "ビ", "bi", "би"),
            Kana(UUID.randomUUID(), "ぶ", "ブ", "bu", "бу"),
            Kana(UUID.randomUUID(), "べ", "ベ", "be", "бэ"),
            Kana(UUID.randomUUID(), "ぼ", "ボ", "bo", "бо"),

            Kana(UUID.randomUUID(), "ぱ", "パ", "pa", "па"),
            Kana(UUID.randomUUID(), "ぴ", "ピ", "pi", "пи"),
            Kana(UUID.randomUUID(), "ぷ", "プ", "pu", "пу"),
            Kana(UUID.randomUUID(), "ぺ", "ペ", "pe", "пэ"),
            Kana(UUID.randomUUID(), "ぽ", "ポ", "po", "по"),

            //soft
            Kana(UUID.randomUUID(), "きゃ", "キャ", "kya", "кя"),
            Kana(UUID.randomUUID(), "きゅ", "キュ", "kyu", "кю"),
            Kana(UUID.randomUUID(), "きょ", "キョ", "kyo", "кё"),

            Kana(UUID.randomUUID(), "しゃ", "シャ", "sha", "ся"),
            Kana(UUID.randomUUID(), "しゅ", "シュ", "shu", "сю"),
            Kana(UUID.randomUUID(), "しょ", "ショ", "sho", "сё"),

            Kana(UUID.randomUUID(), "ちゃ", "チャ", "cha", "тя"),
            Kana(UUID.randomUUID(), "ちゅ", "チュ", "chu", "тю"),
            Kana(UUID.randomUUID(), "ちょ", "チョ", "cho", "тё"),

            Kana(UUID.randomUUID(), "にゃ", "ニャ", "nya", "ня"),
            Kana(UUID.randomUUID(), "にゅ", "ニュ", "nyu", "ню"),
            Kana(UUID.randomUUID(), "にょ", "ニョ", "nyo", "нё"),

            Kana(UUID.randomUUID(), "ひゃ", "ヒャ", "hya", "хя"),
            Kana(UUID.randomUUID(), "ひゅ", "ヒュ", "hyu", "хю"),
            Kana(UUID.randomUUID(), "ひょ", "ヒョ", "hyo", "хё"),

            Kana(UUID.randomUUID(), "みゃ", "ミャ", "mya", "мя"),
            Kana(UUID.randomUUID(), "みゅ", "ミュ", "myu", "мю"),
            Kana(UUID.randomUUID(), "みょ", "ミョ", "myo", "мё"),

            Kana(UUID.randomUUID(), "りゃ", "リャ", "rya", "ря"),
            Kana(UUID.randomUUID(), "りゅ", "リュ", "ryu", "рю"),
            Kana(UUID.randomUUID(), "りょ", "リョ", "ryo", "рё"),

            //soft nigori
            Kana(UUID.randomUUID(), "ぎゃ", "ギャ", "gya", "гя"),
            Kana(UUID.randomUUID(), "ぎゅ", "ギュ", "gyu", "гю"),
            Kana(UUID.randomUUID(), "ぎょ", "ギョ", "gyo", "гё"),

            Kana(UUID.randomUUID(), "じゃ", "ジャ", "ja", "дзя"),
            Kana(UUID.randomUUID(), "じゅ", "ジュ", "ju", "дзю"),
            Kana(UUID.randomUUID(), "じょ", "ジョ", "jo", "дзё"),

            Kana(UUID.randomUUID(), "ぢゃ", "ヂャ", "ja", "дзя"),
            Kana(UUID.randomUUID(), "ぢゅ", "ヂュ", "ju", "дзю"),
            Kana(UUID.randomUUID(), "ぢょ", "ヂョ", "jo", "дзё"),

            Kana(UUID.randomUUID(), "びゃ", "ビャ", "bya", "бя"),
            Kana(UUID.randomUUID(), "びゅ", "ビュ", "byu", "бю"),
            Kana(UUID.randomUUID(), "びょ", "ビョ", "byo", "бё"),

            Kana(UUID.randomUUID(), "ぴゃ", "ピャ", "pya", "пя"),
            Kana(UUID.randomUUID(), "ぴゅ", "ピュ", "pyu", "пю"),
            Kana(UUID.randomUUID(), "ぴょ", "ピョ", "pyo", "пё")
    )*/
}