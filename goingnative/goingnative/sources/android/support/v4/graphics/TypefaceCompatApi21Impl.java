package android.support.v4.graphics;

import android.os.ParcelFileDescriptor;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import java.io.File;
@RequiresApi(21)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
class TypefaceCompatApi21Impl extends TypefaceCompatBaseImpl {
    private static final String TAG = "TypefaceCompatApi21Impl";

    private File getFile(ParcelFileDescriptor parcelFileDescriptor) {
        try {
            String readlink = Os.readlink("/proc/self/fd/" + parcelFileDescriptor.getFd());
            if (OsConstants.S_ISREG(Os.stat(readlink).st_mode)) {
                return new File(readlink);
            }
            return null;
        } catch (ErrnoException unused) {
            return null;
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    @Override // android.support.v4.graphics.TypefaceCompatBaseImpl
    public android.graphics.Typeface createFromFontInfo(android.content.Context r5, android.os.CancellationSignal r6, @android.support.annotation.NonNull android.support.v4.provider.FontsContractCompat.FontInfo[] r7, int r8) {
        /*
            r4 = this;
            int r0 = r7.length
            r1 = 0
            r2 = 1
            if (r0 >= r2) goto L6
            return r1
        L6:
            android.support.v4.provider.FontsContractCompat$FontInfo r7 = r4.findBestInfo(r7, r8)
            android.content.ContentResolver r8 = r5.getContentResolver()
            android.net.Uri r7 = r7.getUri()     // Catch: java.io.IOException -> L7b
            java.lang.String r0 = "r"
            android.os.ParcelFileDescriptor r6 = r8.openFileDescriptor(r7, r0, r6)     // Catch: java.io.IOException -> L7b
            java.io.File r7 = r4.getFile(r6)     // Catch: java.lang.Throwable -> L64
            if (r7 == 0) goto L2f
            boolean r8 = r7.canRead()     // Catch: java.lang.Throwable -> L64
            if (r8 != 0) goto L25
            goto L2f
        L25:
            android.graphics.Typeface r4 = android.graphics.Typeface.createFromFile(r7)     // Catch: java.lang.Throwable -> L64
            if (r6 == 0) goto L2e
            r6.close()     // Catch: java.io.IOException -> L7b
        L2e:
            return r4
        L2f:
            java.io.FileInputStream r7 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L64
            java.io.FileDescriptor r8 = r6.getFileDescriptor()     // Catch: java.lang.Throwable -> L64
            r7.<init>(r8)     // Catch: java.lang.Throwable -> L64
            android.graphics.Typeface r4 = super.createFromInputStream(r5, r7)     // Catch: java.lang.Throwable -> L4a
            if (r7 == 0) goto L41
            r7.close()     // Catch: java.lang.Throwable -> L64
        L41:
            if (r6 == 0) goto L46
            r6.close()     // Catch: java.io.IOException -> L7b
        L46:
            return r4
        L47:
            r4 = move-exception
            r5 = r1
            goto L50
        L4a:
            r4 = move-exception
            throw r4     // Catch: java.lang.Throwable -> L4c
        L4c:
            r5 = move-exception
            r3 = r5
            r5 = r4
            r4 = r3
        L50:
            if (r7 == 0) goto L60
            if (r5 == 0) goto L5d
            r7.close()     // Catch: java.lang.Throwable -> L58
            goto L60
        L58:
            r7 = move-exception
            r5.addSuppressed(r7)     // Catch: java.lang.Throwable -> L64
            goto L60
        L5d:
            r7.close()     // Catch: java.lang.Throwable -> L64
        L60:
            throw r4     // Catch: java.lang.Throwable -> L64
        L61:
            r4 = move-exception
            r5 = r1
            goto L6a
        L64:
            r4 = move-exception
            throw r4     // Catch: java.lang.Throwable -> L66
        L66:
            r5 = move-exception
            r3 = r5
            r5 = r4
            r4 = r3
        L6a:
            if (r6 == 0) goto L7a
            if (r5 == 0) goto L77
            r6.close()     // Catch: java.lang.Throwable -> L72
            goto L7a
        L72:
            r6 = move-exception
            r5.addSuppressed(r6)     // Catch: java.io.IOException -> L7b
            goto L7a
        L77:
            r6.close()     // Catch: java.io.IOException -> L7b
        L7a:
            throw r4     // Catch: java.io.IOException -> L7b
        L7b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.TypefaceCompatApi21Impl.createFromFontInfo(android.content.Context, android.os.CancellationSignal, android.support.v4.provider.FontsContractCompat$FontInfo[], int):android.graphics.Typeface");
    }
}
