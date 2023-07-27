#include <iostream>
#include <string>
#include <cstring>
#include <openssl/md5.h>

std::string md5_hash(const std::string& text) {
    unsigned char result[MD5_DIGEST_LENGTH];
    MD5(reinterpret_cast<const unsigned char*>(text.c_str()), text.length(), result);

    char hex[MD5_DIGEST_LENGTH * 2 + 1];
    for (int i = 0; i < MD5_DIGEST_LENGTH; i++) {
        sprintf(hex + 2 * i, "%02x", result[i]);
    }

    return hex;
}

std::string target_hash = "6e9a4d130a9b316e9201238844dd5124";

void bruteforce_5_chars() {
    const char characters[] = "abcdefghijklmnopqrstuvwxyz0123456789";
    const int len = sizeof(characters) - 1;

    char guess[6] = {}; // 5 characters plus null terminator

    for (int i = 0; i < len; i++) {
        guess[0] = characters[i];
        for (int j = 0; j < len; j++) {
            guess[1] = characters[j];
            for (int k = 0; k < len; k++) {
                guess[2] = characters[k];
                for (int m = 0; m < len; m++) {
                    guess[3] = characters[m];
                    for (int n = 0; n < len; n++) {
                        guess[4] = characters[n];
                        std::string hashed_guess = md5_hash(guess);
                        if (hashed_guess == target_hash) {
                            std::cout << "Guessed 5-character string: " << guess << std::endl;
                            return;
                        }
                    }
                }
            }
        }
    }

    std::cout << "No match found." << std::endl;
}

int main() {
    bruteforce_5_chars();
    return 0;
}
