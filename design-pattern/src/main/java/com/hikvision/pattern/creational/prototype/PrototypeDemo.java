package com.hikvision.pattern.creational.prototype;

public class PrototypeDemo {

    // 生活案例：公章先刻个模板，之后都是复印/克隆
    static class Document implements Cloneable {
        String title;
        String content;
        Document copy() {
            try { return (Document) super.clone(); } catch (CloneNotSupportedException e) { throw new RuntimeException(e); }
        }
        public String toString() { return "标题=" + title + ", 内容=" + content; }
    }

    public static void main(String[] args) {
        Document template = new Document();
        template.title = "合同";
        template.content = "标准条款...";
        Document d2 = template.copy();
        d2.title = "租赁合同";
        System.out.println("模板：" + template);
        System.out.println("副本：" + d2);
    }
}
