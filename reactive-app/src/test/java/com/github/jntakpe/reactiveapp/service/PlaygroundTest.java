package com.github.jntakpe.reactiveapp.service;

import org.junit.Test;
import rx.Observable;

/**
 * Permet de démontrer l'utilisation des observables
 *
 * @author jntakpe
 */
public class PlaygroundTest {

    @Test
    public void lazyObs() {
        Observable.just("Toto", "Titi", "Tata").doOnNext(System.out::println);
    }

    @Test
    public void lazySubObs() {
        Observable.just("Toto", "Titi", "Tata").doOnNext(System.out::println).subscribe();
    }

    @Test
    public void just() {
        Observable.just("Toto", "Titi", "Tata").subscribe(System.out::println);
    }

    @Test
    public void from() {
        String[] arr = {"Toto", "Titi", "Tata"};
        Observable.from(arr).subscribe(System.out::println);
    }

    @Test
    public void create() {
        Observable.create(observer -> {
            try {
                if (!observer.isUnsubscribed()) {
                    observer.onNext("Toto");
                    observer.onNext("Titi");
                    observer.onNext("Tata");
                    observer.onCompleted();
                }
            } catch (Exception e) {
                observer.onError(e);
            }
        }).subscribe(System.out::println);
    }

}
