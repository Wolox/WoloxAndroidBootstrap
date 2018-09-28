import os, shutil

####################### Sanity Steps ###########################
def installGitHooks():
    if not os.path.exists("./git-hooks/pre-commit"):
        print("Skipping git hooks step -- Hooks not present")
        return

    print("Executing git hooks step")
    shutil.copy2("./git-hooks/pre-commit", "../../.git/hooks/pre-commit")

########################### Main ###############################
def main():
    print("Running sanity checks")

    installGitHooks()

if __name__ == "__main__":
    main()