package ninja.ebean;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

public class ReflectionsHelper {
    
    static public Set<String> findAllClassesInPackage(String packageName) {
        try {
            ClassPath cp = ClassPath.from(ReflectionsHelper.class.getClassLoader());
            ImmutableSet<ClassPath.ClassInfo> classes = cp.getTopLevelClasses(packageName);

            Set<String> classNames = new LinkedHashSet<>();

            for (ClassPath.ClassInfo ci : classes) {
                classNames.add(ci.getName());
            }

            return classNames;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
