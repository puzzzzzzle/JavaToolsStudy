package group.zhangtao.design.pattern.decorator;

public class MyPlayer implements Player {
    @Override
    public void useSkills() {
        System.out.println("my layer使用技能!");
    }
}
