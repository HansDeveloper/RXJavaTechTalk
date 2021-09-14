package com.example.rxjavatechtalk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.functions.BiFunction
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.RuntimeException
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //justRXO()
        //intervalRXOp()
        //rangeRXOp()
        //observerOnRXO()
        //mapRXO()
        /*subscribeRXO()
        filterRXO()
        subscribeOnRXO()*/
        //throttlefirstRXO()
        //lastRXO()
        //takeRXO()
        //skipRXO()
        //elementAtRXO()
        //distinctRXO()
        //takeLastRXO()
        //takeWhileRXO()
        //skipLastRXOp()
        //skipwhileRXOp()
        //startWithItemRXOp()
        //repeatRXOp()
        //allRXOp()
        //containsRXOp()
        //toListRXOp()
        //toSortedListRXOp()
        //concatRXOp()
        //concatWithRXOp()
        //mergeRXOp()
        //mergeWithRXOp()
        //delayRXOp()
        //countRXOp()
        //zipRXOp()
        //reduceRXOp()
        onErrorRXOp()
    }

    private fun justRXO(){
        Observable.just(1, 5, 10, 20)
                .subscribe({result ->
                    Log.i("created by just", result.toString())
                })
    }

    private fun intervalRXOp(){
        Observable.interval(1, TimeUnit.SECONDS)
                .subscribe({result ->
                    Log.i("intervaled", result.toString())
                })
    }

    private fun rangeRXO(): Observable<Int>{
        return Observable.range(0, 100)
    }

    private fun rangeRXOp(){
        Observable.range(0, 100)
                .subscribe({result ->
                    Log.i("ranged", result.toString())
                })
    }

    private fun observerOnRXO(){
        Observable.just(1, 2, 4, 5, 10, 20, 40, 80)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map { number ->
                    number * 3
                }.filter {
                    it % 2 == 0
                }.observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.i("utility operators", it.toString())
                })
    }

    //map, just
    private fun mapRXO(){
        Observable.just(1, 5, 10, 20)
                .map { number ->
                    number * 3
                }.subscribe({
            Log.i("mapped", it.toString())
        })
    }

    private fun subscribeRXO(){
        Observable.just(1, 5, 10, 20)
                .map { number ->
                    number * 3
                }.subscribe({
                    Log.i("mapped", it.toString())
                })
    }

    private fun elementAtRXO(){
        Observable.just(1, 2, 4, 5, 10, 20, 40, 80)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .elementAt(4)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({result -> Log.i("fifth_element", result.toString()) })
    }

    private fun filterRXO(){
        Observable.just(1, 2, 4, 5, 10, 20, 40, 80)
                .map { number -> number * 3 }
                .filter { it % 2 == 0 }
                .subscribe({ Log.i("mapped&filtered", it.toString()) })
    }

    private fun lastRXO(){
        Observable.just(1, 2, 4, 5, 10, 20, 40, 80)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .last(0)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({result -> Log.i("last_element", result.toString()) })
    }

    private fun subscribeOnRXO(){
        Observable.just(1, 2, 4, 5, 10, 20, 40, 80)
                .subscribeOn(Schedulers.io())
                .map { number -> number * 3 }
                .filter { it % 2 == 0 }
                .subscribe({ Log.i("io_thread", it.toString()) })
    }

    private fun throttlefirstRXO(){
        Observable.just(1, 2, 4, 5, 10, 20, 40, 80)
                .subscribeOn(Schedulers.io())
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .map {
                    it * 5
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.i("every_second", it.toString())
                })
    }

    /*private fun flatMapRXO(){
        Observable.just(1, 2, 4, 5, 10, 20, 40, 80)
                .subscribeOn(Schedulers.io())
                .flatMap {
                    it * 4.5
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.i("every_second", it.toString())
                })
    }*/

    private fun takeRXO(){
        Observable.just(1, 2, 4, 5, 10, 20, 40, 80)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .take(4)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({result ->
                    Log.i("first_four_elements", result.toString())
                })
    }

    private fun skipRXO(){
        Observable.just(1, 2, 4, 5, 10, 20, 40, 80)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .skip(6)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({result ->
                    Log.i("skip_six_first_elements", result.toString())
                })
    }

    private fun distinctRXO(){
        Observable.just(1, 2, 4, 5, 2, 7, 4, 9)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .distinct()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({result ->
                    Log.i("distinct_elements", result.toString())
                })
    }

    private fun takeLastRXO(){
        rangeRXO()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .takeLast(5)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({result ->
                    Log.i("last five elements", result.toString())
                })
    }

    private fun skipLastRXOp(){
        rangeRXO()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .skipLast(50)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({result ->
                    Log.i("take first 19 elements", result.toString())
                })
    }

    /*private fun skipwhileRXOp(){
        rangeRXO()
                .skipWhile { number ->
                    number < 15
                }.subscribe({result ->
                    Log.i("odd numbers", result.toString())
                })
    }*/

    private fun getAlpabeticalObservable(): Observable<String>{
        return Observable.fromArray("a", "b", "c")
    }

    private fun repeatRXOp(){
        getAlpabeticalObservable()
                .repeat(2)
                .subscribe({result ->
                    Log.i("content twice", result.toString())
                })
    }

    private fun allRXOp(){
        rangeRXO()
                .map{ it * 3 }
                .all { numbers -> numbers % 2 == 0 }
                .subscribe({result -> Log.i("even numbers", result.toString()) })
    }

    private fun containsRXOp(){
        getAlpabeticalObservable()
                .contains("b")
                .subscribe({result -> Log.i("contain b?", result.toString()) })
    }

    private fun takeWhileRXO(){
        rangeRXO()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .takeWhile { number -> number < 20 }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({result -> Log.i("take first 19 elements", result.toString()) })
    }

    private fun unorderedNumbers(): Observable<Int>{
        return Observable.just(
                2,
                67,
                43,
                1,
                90,
                27,
                54,
                550,
                300
        )
    }

    private fun toListRXOp(){
        unorderedNumbers()
                .toList()
                .subscribe({result ->
                    Log.i("list", result.toString())
                })
    }

    private fun toSortedListRXOp(){
        unorderedNumbers()
                .toSortedList()
                .subscribe({result ->
                    Log.i("ordered list", result.toString())
                })
    }

    private fun mergeRXOp(){
        Observable.merge(
                Observable.interval(1, TimeUnit.SECONDS),
                Observable.interval(3, TimeUnit.SECONDS)
        ).subscribe({result -> Log.i("merge", result.toString()) })
    }

    private fun concatRXOp(){
        Observable
                .concat(unorderedNumbers(), unorderedNumbers())
                .subscribe({result ->
                    Log.i("concat", result.toString())
                })
    }

    private fun startWithItemRXOp(){
        getAlpabeticalObservable()
                .startWithItem("z")
                .subscribe({result ->
                    Log.i("start with wxyz", result.toString())
                })
    }

    private fun concatWithRXOp(){
        unorderedNumbers()
                .concatWith(rangeRXO())
                .subscribe({result ->
                    Log.i("concat with", result.toString())
                })
    }



    private fun mergeWithRXOp(){
        Observable
                .interval(1, TimeUnit.SECONDS)
                .mergeWith(Observable.interval(2, TimeUnit.SECONDS))
                .subscribe({result -> Log.i("merge with", result.toString()) })
    }

    /*private fun debounceRXOp(){
        Observable.interval(1, TimeUnit.SECONDS)
                .debounce(950, TimeUnit.MILLISECONDS)
                .subscribe({
                    result -> Log.i("debounced", result.toString())
                })
    }*/

    private fun delayRXOp(){
        unorderedNumbers()
                .delay(7, TimeUnit.SECONDS)
                .subscribe({
                    result -> Log.i("deleyed", result.toString())
                })
    }

    private fun countRXOp(){
        rangeRXO()
                .count()
                .subscribe({
                    result -> Log.i("total", result.toString())
                })
    }

    private fun zipRXOp(){
        val alphabets1 = Observable
                .intervalRange(0, 1, 1, 1, TimeUnit.SECONDS)
                .map { id -> "A" + id }
        val alphabets2 = Observable
                .intervalRange(0, 2, 2, 1, TimeUnit.SECONDS)
                .map { id -> "B" + id }

        Observable.zip(alphabets1, alphabets2,
                BiFunction<String, String, String> { t1, t2 -> "$t1 $t2" }).subscribe({result ->
                    Log.i("zip", result)
        })
    }

    private fun reduceRXOp(){
        unorderedNumbers()
                .reduce { t1: Int?, t2: Int? -> t1?.plus(t2!!) }
                .subscribe({
                    Log.i("reduce", it.toString())
                })
    }

    private fun onErrorRXOp(){
        Observable.fromArray(1, 2 , 3, 4)
                .doOnNext{
                   if (it == 2){
                       throw (RuntimeException("Exception on2"))
                   }
                }
                .onErrorResumeNext { Observable.just(10) }
                .onErrorComplete()
                .doOnComplete { Log.i("onComplete", "completed") }
                .subscribe({
                    Log.i("onSucces", it.toString())
                },{
                    Log.i("onError", it.message.toString())
                })
    }
}

