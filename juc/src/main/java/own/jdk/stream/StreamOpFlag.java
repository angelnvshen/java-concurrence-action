package own.jdk.stream;

import java.util.EnumMap;
import java.util.Map;
import java.util.Spliterator;
enum StreamOpFlag {

    /*
     * Each characteristic takes up 2 bits in a bit set to accommodate
     * preserving, clearing and setting/injecting information.
     *
     * This applies to stream flags, intermediate/terminal operation flags, and
     * combined stream and operation flags. Even though the former only requires
     * 1 bit of information per characteristic, is it more efficient when
     * combining flags to align set and inject bits.
     *
     * Characteristics belong to certain types, see the Type enum. Bit masks for
     * the types are constructed as per the following table:
     *
     *                        DISTINCT  SORTED  ORDERED  SIZED  SHORT_CIRCUIT
     *          SPLITERATOR      01       01       01      01        00
     *               STREAM      01       01       01      01        00
     *                   OP      11       11       11      10        01
     *          TERMINAL_OP      00       00       10      00        01
     * UPSTREAM_TERMINAL_OP      00       00       10      00        00
     *
     * 01 = set/inject
     * 10 = clear
     * 11 = preserve
     *
     * Construction of the columns is performed using a simple builder for
     * non-zero values.
     */


    // The following flags correspond to characteristics on Spliterator
    // and the values MUST be equal.
    //

    /**
     * Characteristic value signifying that, for each pair of
     * encountered elements in a stream {@code x, y}, {@code !x.equals(y)}.
     * <p>
     * A stream may have this value or an intermediate operation can preserve,
     * clear or inject this value.
     */
    // 0, 0x00000001
    // Matches Spliterator.DISTINCT
    DISTINCT(0,
             set(Type.SPLITERATOR).set(Type.STREAM).setAndClear(Type.OP)),

    /**
     * Characteristic value signifying that encounter order follows a natural
     * sort order of comparable elements.
     * <p>
     * A stream can have this value or an intermediate operation can preserve,
     * clear or inject this value.
     * <p>
     * Note: The {@link java.util.Spliterator#SORTED} characteristic can define
     * a sort order with an associated non-null comparator.  Augmenting flag
     * state with addition properties such that those properties can be passed
     * to operations requires some disruptive changes for a singular use-case.
     * Furthermore, comparing comparators for equality beyond that of identity
     * is likely to be unreliable.  Therefore the {@code SORTED} characteristic
     * for a defined non-natural sort order is not mapped internally to the
     * {@code SORTED} flag.
     */
    // 1, 0x00000004
    // Matches Spliterator.SORTED
    SORTED(1,
           set(Type.SPLITERATOR).set(Type.STREAM).setAndClear(Type.OP)),

    /**
     * Characteristic value signifying that an encounter order is
     * defined for stream elements.
     * <p>
     * A stream can have this value, an intermediate operation can preserve,
     * clear or inject this value, or a terminal operation can preserve or clear
     * this value.
     */
    // 2, 0x00000010
    // Matches Spliterator.ORDERED
    ORDERED(2,
            set(Type.SPLITERATOR).set(Type.STREAM).setAndClear(Type.OP).clear(Type.TERMINAL_OP)
                    .clear(Type.UPSTREAM_TERMINAL_OP)),

    /**
     * Characteristic value signifying that size of the stream
     * is of a known finite size that is equal to the known finite
     * size of the source spliterator input to the first stream
     * in the pipeline.
     * <p>
     * A stream can have this value or an intermediate operation can preserve or
     * clear this value.
     */
    // 3, 0x00000040
    // Matches Spliterator.SIZED
    SIZED(3,
          set(Type.SPLITERATOR).set(Type.STREAM).clear(Type.OP)),

    // The following Spliterator characteristics are not currently used but a
    // gap in the bit set is deliberately retained to enable corresponding
    // stream flags if//when required without modification to other flag values.
    //
    // 4, 0x00000100 NONNULL(4, ...
    // 5, 0x00000400 IMMUTABLE(5, ...
    // 6, 0x00001000 CONCURRENT(6, ...
    // 7, 0x00004000 SUBSIZED(7, ...

    // The following 4 flags are currently undefined and a free for any further
    // spliterator characteristics.
    //
    //  8, 0x00010000
    //  9, 0x00040000
    // 10, 0x00100000
    // 11, 0x00400000

    // The following flags are specific to streams and operations
    //

    /**
     * Characteristic value signifying that an operation may short-circuit the
     * stream.
     * <p>
     * An intermediate operation can preserve or inject this value,
     * or a terminal operation can preserve or inject this value.
     */
    // 12, 0x01000000
    SHORT_CIRCUIT(12,
                  set(Type.OP).set(Type.TERMINAL_OP));

    // The following 2 flags are currently undefined and a free for any further
    // stream flags if/when required
    //
    // 13, 0x04000000
    // 14, 0x10000000
    // 15, 0x40000000

    /**
     * Type of a flag
     */
    enum Type {
        /**
         * The flag is associated with spliterator characteristics.
         */
        SPLITERATOR,

        /**
         * The flag is associated with stream flags.
         */
        STREAM,

        /**
         * The flag is associated with intermediate operation flags.
         */
        OP,

        /**
         * The flag is associated with terminal operation flags.
         */
        TERMINAL_OP,

        /**
         * The flag is associated with terminal operation flags that are
         * propagated upstream across the last stateful operation boundary
         */
        UPSTREAM_TERMINAL_OP
    }

    /**
     * The bit pattern for setting/injecting a flag.
     */
    private static final int SET_BITS = 0b01;

    /**
     * The bit pattern for clearing a flag.
     */
    private static final int CLEAR_BITS = 0b10;

    /**
     * The bit pattern for preserving a flag.
     */
    private static final int PRESERVE_BITS = 0b11;

    private static MaskBuilder set(Type t) {
        return new MaskBuilder(new EnumMap<>(Type.class)).set(t);
    }

    private static class MaskBuilder {
        final Map<Type, Integer> map;

        MaskBuilder(Map<Type, Integer> map) {
            this.map = map;
        }

        MaskBuilder mask(Type t, Integer i) {
            map.put(t, i);
            return this;
        }

        MaskBuilder set(Type t) {
            return mask(t, SET_BITS);
        }

        MaskBuilder clear(Type t) {
            return mask(t, CLEAR_BITS);
        }

        MaskBuilder setAndClear(Type t) {
            return mask(t, PRESERVE_BITS);
        }

        Map<Type, Integer> build() {
            for (Type t : Type.values()) {
                map.putIfAbsent(t, 0b00);
            }
            return map;
        }
    }

    /**
     * The mask table for a flag, this is used to determine if a flag
     * corresponds to a certain flag type and for creating mask constants.
     */
    private final Map<Type, Integer> maskTable;

    /**
     * The bit position in the bit mask.
     */
    private final int bitPosition;

    /**
     * The set 2 bit set offset at the bit position.
     */
    private final int set;

    /**
     * The clear 2 bit set offset at the bit position.
     */
    private final int clear;

    /**
     * The preserve 2 bit set offset at the bit position.
     */
    private final int preserve;

    private StreamOpFlag(int position, MaskBuilder maskBuilder) {
        this.maskTable = maskBuilder.build();
        // Two bits per flag
        position *= 2;
        this.bitPosition = position;
        this.set = SET_BITS << position;
        this.clear = CLEAR_BITS << position;
        this.preserve = PRESERVE_BITS << position;
    }

    @Override
    public String toString() {
        return "StreamOpFlag{" +
            "maskTable=" + maskTable +
            ", bitPosition=" + bitPosition +
            ", set=" + set +
            ", clear=" + clear +
            ", preserve=" + preserve +
            "} " + super.toString();
    }

    /**
     * Gets the bitmap associated with setting this characteristic.
     *
     * @return the bitmap for setting this characteristic
     */
    int set() {
        return set;
    }

    /**
     * Gets the bitmap associated with clearing this characteristic.
     *
     * @return the bitmap for clearing this characteristic
     */
    int clear() {
        return clear;
    }

    /**
     * Determines if this flag is a stream-based flag.
     *
     * @return true if a stream-based flag, otherwise false.
     */
    boolean isStreamFlag() {
        return maskTable.get(Type.STREAM) > 0;
    }

    /**
     * Checks if this flag is set on stream flags, injected on operation flags,
     * and injected on combined stream and operation flags.
     *
     * @param flags the stream flags, operation flags, or combined stream and
     *        operation flags
     * @return true if this flag is known, otherwise false.
     */
    boolean isKnown(int flags) {
        return (flags & preserve) == set;
    }

    /**
     * Checks if this flag is cleared on operation flags or combined stream and
     * operation flags.
     *
     * @param flags the operation flags or combined stream and operations flags.
     * @return true if this flag is preserved, otherwise false.
     */
    boolean isCleared(int flags) {
        return (flags & preserve) == clear;
    }

    /**
     * Checks if this flag is preserved on combined stream and operation flags.
     *
     * @param flags the combined stream and operations flags.
     * @return true if this flag is preserved, otherwise false.
     */
    boolean isPreserved(int flags) {
        return (flags & preserve) == preserve;
    }

    /**
     * Determines if this flag can be set for a flag type.
     *
     * @param t the flag type.
     * @return true if this flag can be set for the flag type, otherwise false.
     */
    boolean canSet(Type t) {
        return (maskTable.get(t) & SET_BITS) > 0;
    }

    /**
     * The bit mask for spliterator characteristics
     */
    static final int SPLITERATOR_CHARACTERISTICS_MASK = createMask(Type.SPLITERATOR);

    /**
     * The bit mask for source stream flags.
     */
    static final int STREAM_MASK = createMask(Type.STREAM);

    /**
     * The bit mask for intermediate operation flags.
     */
    static final int OP_MASK = createMask(Type.OP);

    /**
     * The bit mask for terminal operation flags.
     */
    static final int TERMINAL_OP_MASK = createMask(Type.TERMINAL_OP);

    /**
     * The bit mask for upstream terminal operation flags.
     */
    static final int UPSTREAM_TERMINAL_OP_MASK = createMask(Type.UPSTREAM_TERMINAL_OP);

    private static int createMask(Type t) {
        int mask = 0;
        for (StreamOpFlag flag : StreamOpFlag.values()) {
            mask |= flag.maskTable.get(t) << flag.bitPosition;
        }
        return mask;
    }

    /**
     * Complete flag mask.
     */
    private static final int FLAG_MASK = createFlagMask();

    private static int createFlagMask() {
        int mask = 0;
        for (StreamOpFlag flag : StreamOpFlag.values()) {
            mask |= flag.preserve;
        }
        return mask;
    }

    /**
     * Flag mask for stream flags that are set.
     */
    private static final int FLAG_MASK_IS = STREAM_MASK;

    /**
     * Flag mask for stream flags that are cleared.
     */
    private static final int FLAG_MASK_NOT = STREAM_MASK << 1;

    /**
     * The initial value to be combined with the stream flags of the first
     * stream in the pipeline.
     */
    static final int INITIAL_OPS_VALUE = FLAG_MASK_IS | FLAG_MASK_NOT;

    /**
     * The bit value to set or inject {@link #DISTINCT}.
     */
    static final int IS_DISTINCT = DISTINCT.set;

    /**
     * The bit value to clear {@link #DISTINCT}.
     */
    static final int NOT_DISTINCT = DISTINCT.clear;

    /**
     * The bit value to set or inject {@link #SORTED}.
     */
    static final int IS_SORTED = SORTED.set;

    /**
     * The bit value to clear {@link #SORTED}.
     */
    static final int NOT_SORTED = SORTED.clear;

    /**
     * The bit value to set or inject {@link #ORDERED}.
     */
    static final int IS_ORDERED = ORDERED.set;

    /**
     * The bit value to clear {@link #ORDERED}.
     */
    static final int NOT_ORDERED = ORDERED.clear;

    /**
     * The bit value to set {@link #SIZED}.
     */
    static final int IS_SIZED = SIZED.set;

    /**
     * The bit value to clear {@link #SIZED}.
     */
    static final int NOT_SIZED = SIZED.clear;

    /**
     * The bit value to inject {@link #SHORT_CIRCUIT}.
     */
    static final int IS_SHORT_CIRCUIT = SHORT_CIRCUIT.set;

    private static int getMask(int flags) {
        return (flags == 0)
               ? FLAG_MASK
               : ~(flags | ((FLAG_MASK_IS & flags) << 1) | ((FLAG_MASK_NOT & flags) >> 1));
    }

    /**
     * Combines stream or operation flags with previously combined stream and
     * operation flags to produce updated combined stream and operation flags.
     * <p>
     * A flag set on stream flags or injected on operation flags,
     * and injected combined stream and operation flags,
     * will be injected on the updated combined stream and operation flags.
     *
     * <p>
     * A flag set on stream flags or injected on operation flags,
     * and cleared on the combined stream and operation flags,
     * will be cleared on the updated combined stream and operation flags.
     *
     * <p>
     * A flag set on the stream flags or injected on operation flags,
     * and preserved on the combined stream and operation flags,
     * will be injected on the updated combined stream and operation flags.
     *
     * <p>
     * A flag not set on the stream flags or cleared/preserved on operation
     * flags, and injected on the combined stream and operation flags,
     * will be injected on the updated combined stream and operation flags.
     *
     * <p>
     * A flag not set on the stream flags or cleared/preserved on operation
     * flags, and cleared on the combined stream and operation flags,
     * will be cleared on the updated combined stream and operation flags.
     *
     * <p>
     * A flag not set on the stream flags,
     * and preserved on the combined stream and operation flags
     * will be preserved on the updated combined stream and operation flags.
     *
     * <p>
     * A flag cleared on operation flags,
     * and preserved on the combined stream and operation flags
     * will be cleared on the updated combined stream and operation flags.
     *
     * <p>
     * A flag preserved on operation flags,
     * and preserved on the combined stream and operation flags
     * will be preserved on the updated combined stream and operation flags.
     *
     * @param newStreamOrOpFlags the stream or operation flags.
     * @param prevCombOpFlags previously combined stream and operation flags.
     *        The value {#link INITIAL_OPS_VALUE} must be used as the seed value.
     * @return the updated combined stream and operation flags.
     */
    static int combineOpFlags(int newStreamOrOpFlags, int prevCombOpFlags) {
        // 0x01 or 0x10 nibbles are transformed to 0x11
        // 0x00 nibbles remain unchanged
        // Then all the bits are flipped
        // Then the result is logically or'ed with the operation flags.
        return (prevCombOpFlags & StreamOpFlag.getMask(newStreamOrOpFlags)) | newStreamOrOpFlags;
    }

    /**
     * Converts combined stream and operation flags to stream flags.
     *
     * <p>Each flag injected on the combined stream and operation flags will be
     * set on the stream flags.
     *
     * @param combOpFlags the combined stream and operation flags.
     * @return the stream flags.
     */
    static int toStreamFlags(int combOpFlags) {
        // By flipping the nibbles 0x11 become 0x00 and 0x01 become 0x10
        // Shift left 1 to restore set flags and mask off anything other than the set flags
        return ((~combOpFlags) >> 1) & FLAG_MASK_IS & combOpFlags;
    }

    /**
     * Converts stream flags to a spliterator characteristic bit set.
     *
     * @param streamFlags the stream flags.
     * @return the spliterator characteristic bit set.
     */
    static int toCharacteristics(int streamFlags) {
        return streamFlags & SPLITERATOR_CHARACTERISTICS_MASK;
    }

    /**
     * Converts a spliterator characteristic bit set to stream flags.
     *
     * @implSpec
     * If the spliterator is naturally {@code SORTED} (the associated
     * {@code Comparator} is {@code null}) then the characteristic is converted
     * to the {@link #SORTED} flag, otherwise the characteristic is not
     * converted.
     *
     * @param spliterator the spliterator from which to obtain characteristic
     *        bit set.
     * @return the stream flags.
     */
    static int fromCharacteristics(Spliterator<?> spliterator) {
        int characteristics = spliterator.characteristics();
        if ((characteristics & Spliterator.SORTED) != 0 && spliterator.getComparator() != null) {
            // Do not propagate the SORTED characteristic if it does not correspond
            // to a natural sort order
            return characteristics & SPLITERATOR_CHARACTERISTICS_MASK & ~Spliterator.SORTED;
        }
        else {
            return characteristics & SPLITERATOR_CHARACTERISTICS_MASK;
        }
    }

    /**
     * Converts a spliterator characteristic bit set to stream flags.
     *
     * @param characteristics the spliterator characteristic bit set.
     * @return the stream flags.
     */
    static int fromCharacteristics(int characteristics) {
        return characteristics & SPLITERATOR_CHARACTERISTICS_MASK;
    }
}
