package just.monika.thaumaturgy.things.power;

public record ThaumaturgyRecord(
        double intensity,
        HueScale hueScale,
        Pitch pitch,
        Varieties varieties
) {
    public static final ThaumaturgyRecord NORMALCY =
            new ThaumaturgyRecord(
                    100,
                    HueScale.Ruby,
                    Pitch.Natural,
                    Varieties.Sparse
            );
    public enum Varieties{
        Sparse, Loose, Tight, Locked
    }
    public enum Pitch{
        DoubleFlat, Flat, Natural, Sharp, DoubleSharp
    }
    public enum HueScale{
        Ruby, Topaz, Lemon, Malachite, Sapphire, Ebony, OverEbony
    }
    public ThaumaturgyRecord getBacklash(){
        return new ThaumaturgyRecord(
                intensity / 1.8,
                HueScale.values()[6 - hueScale.ordinal()],
                Pitch.values()[4 - pitch.ordinal()],
                varieties
        );
    }
}
