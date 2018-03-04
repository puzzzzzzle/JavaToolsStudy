package group.zhangtao.design.pattern.decorator;

public class AnotherPlayer implements Player {
    @Override
    public void useSkills() {
        System.out.println("another player使用技能！");
    }
}
