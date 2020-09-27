package com.dh.common.util;



public class Singleton {
  private Singleton(){}

  private static class holder{
      private static Singleton singleton = new Singleton();
  }

  public Singleton getInstance(){
      return holder.singleton;
  }

}
