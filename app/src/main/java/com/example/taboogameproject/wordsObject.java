package com.example.taboogameproject;

public class wordsObject
{
    String mainWord;
    String tabooWord1;

    public wordsObject()
    {}

    public wordsObject(String mainWord1, String tabooWord11)
    {
        mainWord = mainWord1;
        tabooWord1 = tabooWord11;
    }

    public String getMainWord() {
        return mainWord;
    }

    public void setMainWord(String mainWord) {
        this.mainWord = mainWord;
    }

    public String getTabooWord1()
    {
        return tabooWord1;
    }

    public void setTabooWord1(String tabooWord1)
    {
        this.tabooWord1 = tabooWord1;
    }
}
