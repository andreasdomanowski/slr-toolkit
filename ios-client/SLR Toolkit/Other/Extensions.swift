import Foundation
import SwiftyBibtex

extension String {
    private static let latexReplacements = [
        ("a", "'", "á"),
        ("e", "'", "é"),
        ("i", "'", "í"),
        ("o", "'", "ó"),
        ("u", "'", "ú"),
        ("a", "\"", "ä"),
        ("e", "\"", "ë"),
        ("o", "\"", "ö"),
        ("u", "\"", "ü"),
        ("a", "^", "â"),
        ("e", "^", "ê"),
        ("i", "^", "î"),
        ("n", "~", "ñ")
    ].flatMap { replacements(for: $0.0, with: $0.1, replacement: $0.2) } + [
        ("{-}", "-"),
        ("{\\o}", "ø"),
        ("{\\O}", "Ø"),
        ("{\\c{c}}", "ç"),
        ("{\\C{c}}", "Ç"),
        ("{\\v{s}}", "š"),
        ("{\\ss}", "ß"),
        ("\\ss", "ß")
    ]

    private static func replacements(for letter: String, with diacritic: String, replacement: String) -> [(String, String)] {
        return [
            ("{\\\(diacritic){\\\(letter)}}", replacement),
            ("{\\\(diacritic){\(letter)}}", replacement),
            ("{\\\(diacritic)\(letter)}", replacement),
            ("{\\\(diacritic)\\\(letter)}", replacement),
            ("\\\(diacritic){\\\(letter)}", replacement),
            ("\\\(diacritic){\(letter)}", replacement),
            ("\\\(diacritic)\(letter)", replacement)
        ] + (letter == letter.uppercased() ? [] : replacements(for: letter.uppercased(), with: diacritic, replacement: replacement.uppercased()))
    }

    var withLatexMacrosReplaced: String {
        var string = self
        for (s, r) in Self.latexReplacements {
            string = string.replacingOccurrences(of: s, with: r)
        }
        return string
    }
}

extension FileManager {
    func contentsOfDirectory(at url: URL, matching predicate: (Bool, String) -> Bool) -> [URL] {
        do {
            let contents = try contentsOfDirectory(at: url, includingPropertiesForKeys: [.isDirectoryKey])
            return contents.filter { url in
                do {
                    let resourceValues = try url.resourceValues(forKeys: [.isDirectoryKey])
                    return predicate(resourceValues.isDirectory!, url.pathComponents.last!)
                } catch {
                    print("Error fetching resource values for \(url): \(error)")
                    return false
                }
            }
        } catch {
            print("Error listing contents of \(url): \(error)")
            return []
        }
    }
}

extension Publication {
    var title: String {
        return fields["title"]?.withLatexMacrosReplaced ?? "No Title"
    }

    var author: String? {
        return fields["author"]?.withLatexMacrosReplaced
    }

    var date: String? {
        if let yearString = fields["year"] {
            if let monthString = fields["month"], let month = Month(monthString) {
                return "\(month) \(yearString)"
            }
            return yearString
        }
        return nil
    }

    var abstract: String {
        return fields["abstract"] ?? "No Abstract"
    }
}
