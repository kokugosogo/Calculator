# Calculator
Javaを用いたGUIで入力できる電卓

# DEMO
![](https://user-images.githubusercontent.com/72292551/109921682-9a2bd280-7cff-11eb-80d6-7a39544900e7.gif)

# Features
* 2つの実数の四則演算ができる電卓
* 演算子とオペランドの判定には正規表現を用いている

# Requirement
* jdk 14.0.2
* javafx sdk 11.0.2

# Usage
* コンパイルコマンド
```bash
javac -p "保存したjavafxのlibファイルへのpath" --add-modules javafx.controls Caluculator.java
```
* 実行コマンド
```bash
java -p "保存したjavafxのlibファイルへのpath" --add-modules javafx.controls Caluculator
```

# Note
* 3つの実数を同時に計算はできないので、2つずつ計算してください

# Author
* https://github.com/kokugosogo
